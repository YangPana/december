package com.yarns.december.controller;

import com.yarns.december.entity.base.ResponseBo;
import com.yarns.december.entity.generator.GeneratorConfig;
import com.yarns.december.entity.generator.GeneratorConfigEditBo;
import com.yarns.december.service.GeneratorConfigService;
import com.yarns.december.support.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Yarns
 */
@Slf4j
@RestController
@RequestMapping("generatorConfig")
@RequiredArgsConstructor
public class GeneratorConfigController {

    private final GeneratorConfigService generatorConfigService;

    @GetMapping
    public ResponseBo getGeneratorConfig() {
        return ResponseBo.result(generatorConfigService.findGeneratorConfig());
    }

    @PostMapping
    public ResponseBo updateGeneratorConfig(@Valid @RequestBody GeneratorConfigEditBo generatorConfig) throws BaseException {
        if (StringUtils.isBlank(generatorConfig.getId())) {
            return ResponseBo.warnMsg("配置id不能为空");
        }
        GeneratorConfig g = new GeneratorConfig();
        BeanUtils.copyProperties(generatorConfig,g);
        this.generatorConfigService.updateGeneratorConfig(g);
        return ResponseBo.ok();
    }
}
