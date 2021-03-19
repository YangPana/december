package com.yarns.december.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yarns.december.entity.generator.GeneratorConfig;

/**
 * @author MrBird
 */
public interface GeneratorConfigService extends IService<GeneratorConfig> {

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);

}
