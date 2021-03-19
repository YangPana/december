package com.yarns.december.entity.generator;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Yarns
 */
@Data
@Alias("table")
public class Table {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
