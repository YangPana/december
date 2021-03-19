package com.yarns.december.support.constant;

/**
 * 系统常量
 * @author Yarns
 */
public class Constant {

    /**
     * 基础用户角色ID
     */
    public static final Long BASIC_ROLE_ID = 1L;

    /**
     * 图片存放的路径
     */
    public static final String STATIC_IMAGES_FOLDER = "images";

    /**
     * 视频存放的路径
     */
    public static final String STATIC_VIDEOS_FOLDER = "videos";

    /**
     * 音频存放的路径
     */
    public static final String STATIC_AUDIOS_FOLDER = "audios";

    /**
     * office存放的路径
     */
    public static final String STATIC_OFFICES_FOLDER = "offices";

    /**
     * 其他文件存放的路径
     */
    public static final String OTHERS_FILES_FOLDER = "others";
    /**
     * 图片后缀范围, 正则表达式, 必须小写
     */
    public static final String IMAGE_SUFFIXES_SCOPE = "jpg|gif|png|jpeg";

    /**
     * 视频后缀范围, 正则表达式, 必须小写
     */
    public static final String VIDEO_SUFFIXES_SCOPE = "mp4|flv|f4v|webm|m3u8|rmvb|avi|3gp|rm";

    /**
     * 音频后缀范围, 正则表达式, 必须小写
     */
    public static final String AUDIO_SUFFIXES_SCOPE = "mp3|wav|ogg|flac|m4a|mp4|aac|wma";

    /**
     * office后缀范围, 正则表达式, 必须小写
     */
    public static final String OFFICE_SUFFIXES_SCOPE = "doc|docx|ppt|pptx|xls|xlsx|pdf";

    /**
     * 压缩文件后缀范围, 正则表达式, 必须小写
     */
    public static final String THUMB_SUFFIXES_SCOPE = "zip|rar|gzip|jar|7z";
    /**
     * 限流码
     */
    public static final String GATEWAY_LIMIT_CODE = "gatewayLimit:code:";

    /**
     * 限流计数器
     */
    public static final String GATEWAY_LIMIT_NUMBER = "gatewayLimit:number:";


    /**
     * redis存储用户导入时的用户登录账号 key
     */
    public static final String IMPORT_USER_REDIS = "import:user:";


    /**
     * redis存储授权码 key
     */
    public static final String AUTHORIZATION_CODE = "authorization:code:";

    /**
     * 异步线程池名称
     */
    public static final String ASYNC_POOL = "AsyncThreadPool";

    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    public static final String OAUTH2_TOKEN_TYPE = "bearer";

    /**
     * 排序规则：降序
     */
    public static final String ORDER_DESC = "descending";
    /**
     * 排序规则：升序
     */
    public static final String ORDER_ASC = "ascending";

    /**
     * 允许下载的文件类型，根据需求自己添加（小写）
     */
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip"};


    /**
     * Java默认临时目录
     */
    public static final String JAVA_TEMP_DIR = "java.io.tmpdir";
    /**
     * utf-8
     */
    public static final String UTF8 = "utf-8";

    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "captcha.";

    public static final String LOCALHOST = "localhost";
    public static final String LOCALHOST_IP = "127.0.0.1";
}
