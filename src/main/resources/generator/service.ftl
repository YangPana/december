package ${basePackage}.${servicePackage};

import ${basePackage}.${entityPackage}.${className};

import com.yarns.december.entity.base.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * ${tableComment} Service接口
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className}Service extends IService<${className}> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return IPage<${className}>
     */
    IPage<${className}> find${className}s(QueryRequest request, ${className} ${className?uncap_first});

    /**
     * 新增${tableComment}
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void create${className}(${className} ${className?uncap_first});

    /**
     * 修改${tableComment}
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void update${className}(${className} ${className?uncap_first});

    /**
     * 删除${tableComment}
     *
     * @param ${className?uncap_first}Ids ${tableComment} id数组
     */
    void delete${className}s(String[] ${className?uncap_first}Ids);
}
