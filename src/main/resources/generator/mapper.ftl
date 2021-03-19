package ${basePackage}.${mapperPackage};

import ${basePackage}.${entityPackage}.${className};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * ${tableComment} Mapper
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className}Mapper extends BaseMapper<${className}> {

    /**
     * 查询（分页）
     *
     * @param page Page
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return IPage<${className}>
     */
    IPage<${className}> find${className}DetailPage(Page page,@Param("${className?uncap_first}")  ${className} ${className?uncap_first});
}
