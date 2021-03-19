package com.yarns.december.entity.generator;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Yarns
 */
@Data
@Alias("column")
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;
}
