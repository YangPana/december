package com.yarns.december.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yarns.december.entity.base.QueryRequest;
import com.yarns.december.entity.generator.Column;
import com.yarns.december.entity.generator.Table;

import java.util.List;

/**
 * @author Yarns
 */
public interface GeneratorService {

    List<String> getDatabases(String databaseType);

    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}
