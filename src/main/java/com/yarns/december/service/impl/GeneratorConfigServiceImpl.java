package com.yarns.december.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yarns.december.entity.generator.GeneratorConfig;
import com.yarns.december.mapper.GeneratorConfigMapper;
import com.yarns.december.service.GeneratorConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yarns
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GeneratorConfigServiceImpl extends ServiceImpl<GeneratorConfigMapper, GeneratorConfig> implements GeneratorConfigService {

    @Override
    public GeneratorConfig findGeneratorConfig() {
        List<GeneratorConfig> generatorConfigs = this.baseMapper.selectList(null);
        return CollectionUtils.isNotEmpty(generatorConfigs) ? generatorConfigs.get(0) : null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGeneratorConfig(GeneratorConfig generatorConfig) {
        this.saveOrUpdate(generatorConfig);
    }
}
