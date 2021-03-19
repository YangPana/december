package com.yarns.december.support.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.base.Preconditions;
import com.yarns.december.support.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Yarns
 */
@Slf4j
public class FileUtil {

    private static final int BUFFER = 1024 * 8;

    /**
     * 获取根据日期生成的文件名称
     * @param originalFilename 原本的文件名称
     * @return 根据日期生成的文件名称
     */
    synchronized public static String getDateFileName(String originalFilename){
        //获取文件后缀
        String ext = getFileExt(originalFilename);
        //生成时间关联的文件名
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"_"+System.currentTimeMillis()+"."+ext;
    }

    /**
     * 根据文件名返回文件夹路径
     * @param fileName 文件名
     * @return
     */
    public static String getFolderByType(String fileName,String rootPath){
        if(isImage(fileName)){
            return rootPath+ Constant.STATIC_IMAGES_FOLDER+StringPool.SLASH;
        }else if(isVideo(fileName)){
            return rootPath+Constant.STATIC_VIDEOS_FOLDER+StringPool.SLASH;
        }else if(isOffice(fileName)){
            return rootPath+Constant.STATIC_OFFICES_FOLDER+StringPool.SLASH;
        }else if(isAudio(fileName)){
            return rootPath+Constant.STATIC_AUDIOS_FOLDER+StringPool.SLASH;
        }else {
            return rootPath+Constant.OTHERS_FILES_FOLDER+StringPool.SLASH;
        }
    }

    /**
     * 压缩文件或目录
     *
     * @param fromPath 待压缩文件或路径
     * @param toPath   压缩文件，如 xx.zip
     */
    public static void compress(String fromPath, String toPath) throws IOException {
        File fromFile = new File(fromPath);
        File toFile = new File(toPath);
        if (!fromFile.exists()) {
            throw new FileNotFoundException(fromPath + "不存在！");
        }
        try (
                FileOutputStream outputStream = new FileOutputStream(toFile);
                CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
                ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream)
        ) {
            String baseDir = "";
            compress(fromFile, zipOutputStream, baseDir);
        }
    }

    /**
     * 文件下载
     *
     * @param filePath 待下载文件路径
     * @param fileName 下载文件名称
     * @param delete   下载后是否删除源文件
     * @param response HttpServletResponse
     * @throws Exception Exception
     */
    public static void download(String filePath, String fileName, Boolean delete, HttpServletResponse response) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件未找到");
        }

        String fileType = getFileType(file);
        if (!fileTypeIsValid(fileType)) {
            throw new Exception("暂不支持该类型文件下载");
        }
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + java.net.URLEncoder.encode(fileName, Constant.UTF8));
        response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
        response.setCharacterEncoding(Constant.UTF8);
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            if (delete) {
                delete(filePath);
            }
        }
    }

    /**
     * 递归删除文件或目录
     *
     * @param filePath 文件或目录
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(f -> delete(f.getPath()));
            }
        }
        file.delete();
    }

    /**
     * 获取文件类型
     *
     * @param file 文件
     * @return 文件类型
     * @throws Exception Exception
     */
    private static String getFileType(File file) throws Exception {
        Preconditions.checkNotNull(file);
        if (file.isDirectory()) {
            throw new Exception("file不是文件");
        }
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    /**
     * 校验文件类型是否是允许下载的类型
     *
     * @param fileType fileType
     * @return Boolean
     */
    private static Boolean fileTypeIsValid(String fileType) {
        Preconditions.checkNotNull(fileType);
        fileType = StringUtils.lowerCase(fileType);
        return ArrayUtils.contains(Constant.VALID_FILE_TYPE, fileType);
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null && ArrayUtils.isNotEmpty(files)) {
            for (File file : files) {
                compress(file, zipOut, baseDir + dir.getName() + File.separator);
            }
        }
    }

    private static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (!file.exists()) {
            return;
        }
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zipOut.putNextEntry(entry);
            int count;
            byte[] data = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                zipOut.write(data, 0, count);
            }
        }
    }

    /**
     * 得到文件的扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        int potPos = fileName.lastIndexOf('.') + 1;
        return fileName.substring(potPos, fileName.length());
    }

    /**
     * 检查文件名称是否为指定格式的图片
     * @param filename 文件名称
     * @return
     */
    public static boolean isImage(String filename){
        return getFileExt(filename).toLowerCase().matches(Constant.IMAGE_SUFFIXES_SCOPE);
    }

    /**
     * 检查文件名称是否为指定格式的图片
     * @param file MultipartFile类型的对象
     * @return
     */
    public static boolean isImage(MultipartFile file) {
        return file != null && file.getSize() != 0 && isImage(file.getOriginalFilename());
    }

    /**
     * 检查文件名称是否为指定格式的图片
     * @param file File类型的对象
     * @return
     */
    public static boolean isImage(File file) {
        return file != null && file.exists() && isImage(file.getName());
    }

    /**
     * 检查文件名称是否为指定格式的视频
     * @param filename 文件名称
     * @return
     */
    public static boolean isVideo(String filename){
        return getFileExt(filename).toLowerCase().matches(Constant.VIDEO_SUFFIXES_SCOPE);
    }

    /**
     * 检查文件名称是否为指定格式的视频
     * @param file MultipartFile类型的对象
     * @return
     */
    public static boolean isVideo(MultipartFile file) {
        return file != null && file.getSize() != 0 && isVideo(file.getOriginalFilename());
    }

    /**
     * 检查文件名称是否为指定格式的视频
     * @param file File类型的对象
     * @return
     */
    public static boolean isVideo(File file) {
        return file != null && file.exists() && isVideo(file.getName());
    }

    /**
     * 检查文件名称是否为指定格式的音频
     * @param filename 文件名称
     * @return
     */
    public static boolean isAudio(String filename){
        return getFileExt(filename).toLowerCase().matches(Constant.AUDIO_SUFFIXES_SCOPE);
    }

    /**
     * 检查文件名称是否为指定格式的音频
     * @param file MultipartFile类型的对象
     * @return
     */
    public static boolean isAudio(MultipartFile file) {
        return file != null && file.getSize() != 0 && isAudio(file.getOriginalFilename());
    }

    /**
     * 检查文件名称是否为指定格式的音频
     * @param file File类型的对象
     * @return
     */
    public static boolean isAudio(File file) {
        return file != null && file.exists() && isAudio(file.getName());
    }

    /**
     * 检查文件名称是否为指定格式的office
     * @param filename 文件名称
     * @return
     */
    public static boolean isOffice(String filename){
        return getFileExt(filename).toLowerCase().matches(Constant.OFFICE_SUFFIXES_SCOPE);
    }

    /**
     * 检查文件名称是否为指定格式的office
     * @param file MultipartFile类型的对象
     * @return
     */
    public static boolean isOffice(MultipartFile file) {
        return file != null && file.getSize() != 0 && isOffice(file.getOriginalFilename());
    }

    /**
     * 检查文件名称是否为指定格式的office
     * @param file File类型的对象
     * @return
     */
    public static boolean isOffice(File file) {
        return file != null && file.exists() && isOffice(file.getName());
    }

    /**
     * 检查文件名称是否为指定格式的压缩文件
     * @param filename 文件名称
     * @return
     */
    public static boolean isThumb(String filename){
        return getFileExt(filename).toLowerCase().matches(Constant.THUMB_SUFFIXES_SCOPE);
    }

    /**
     * 检查文件名称是否为指定格式的压缩文件
     * @param file MultipartFile类型的对象
     * @return
     */
    public static boolean isThumb(MultipartFile file) {
        return file != null && file.getSize() != 0 && isThumb(file.getOriginalFilename());
    }

    /**
     * 检查文件名称是否为指定格式的压缩文件
     * @param file File类型的对象
     * @return
     */
    public static boolean isThumb(File file) {
        return file != null && file.exists() && isThumb(file.getName());
    }


    /**
     * inputStream转File
     * @param inputStream
     * @param tempDir
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File coverInputStreamToFile(InputStream inputStream,String tempDir,String fileName) throws IOException {
        if(StringUtils.isBlank(tempDir)){
            tempDir = Constant.JAVA_TEMP_DIR+StringPool.SLASH+fileName;
        }
        File targetFile = new File(tempDir);
        FileUtils.copyInputStreamToFile(inputStream, targetFile);
        return targetFile;
    }


//    /**
//     * 将字节数组转为pdf的输入流（作为下一个输入使用）
//     *
//     * @param bytes
//     * @return
//     * @throws XrBusinessException
//     */
//    public static InputStream getInputStreamPDF(byte[] bytes) throws XrBusinessException {
//        try {
//            Document nodes = new Document(new ByteArrayInputStream(bytes));
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            nodes.save(byteArrayOutputStream, SaveFormat.PDF);
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//            return byteArrayInputStream;
//        }catch (Exception e) {
//            throw new XrBusinessException("生成pdf失败");
//        }
//    }
}
