package com.yarns.december.entity.generator;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Yarns
 */
@Data
public class GeneratorConfigEditBo implements Serializable {
    private static final long serialVersionUID = 6957939298032758629L;

    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 作者
     */
    @Size(max = 20, message = "{noMoreThan}")
    private String author;

    /**
     * 基础包名
     */
    @Size(max = 50, message = "{noMoreThan}")
    private String basePackage;

    /**
     * entity文件存放路径
     */
    @Size(max = 20, message = "{noMoreThan}")
    private String entityPackage;

    /**
     * mapper文件存放路径
     */
    @Size(max = 20, message = "{noMoreThan}")
    private String mapperPackage;

    /**
     * mapper xml文件存放路径
     */
    @Size(max = 20, message = "{noMoreThan}")
    private String mapperXmlPackage;

    /**
     * servcie文件存放路径
     */
    private String servicePackage;

    /**
     * serviceImpl文件存放路径
     */
    @Size(max = 20, message = "{noMoreThan}")
    private String serviceImplPackage;

    /**
     * controller文件存放路径
     */
    @Size(max = 20, message = "{noMoreThan}")
    private String controllerPackage;

    /**
     * 是否去除前缀
     */
    private String isTrim;

    /**
     * 前缀内容
     */
    private String trimValue;
}
