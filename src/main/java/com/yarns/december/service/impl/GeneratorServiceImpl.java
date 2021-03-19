package com.yarns.december.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yarns.december.entity.base.QueryRequest;
import com.yarns.december.entity.generator.Column;
import com.yarns.december.entity.generator.Table;
import com.yarns.december.mapper.GeneratorMapper;
import com.yarns.december.service.GeneratorService;
import com.yarns.december.support.constant.Constant;
import com.yarns.december.support.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yarns
 */
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private final GeneratorMapper generatorMapper;

    @Override
    public List<String> getDatabases(String databaseType) {
        return generatorMapper.getDatabases(databaseType);
    }

    @Override
    public IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName) {
        Page<Table> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", Constant.ORDER_ASC, false);
        return generatorMapper.getTables(page, tableName, databaseType, schemaName);
    }

    @Override
    public List<Column> getColumns(String databaseType, String schemaName, String tableName) {
        return generatorMapper.getColumns(databaseType, schemaName, tableName);
    }
}
