package com.yarns.december.entity.base;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Yarns
 */
@Alias("res")
public class ResponseBo extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -8713837118340960775L;

    public ResponseBo message(String message) {
        this.put("message", message);
        return this;
    }

    public ResponseBo data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public ResponseBo put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static ResponseBo ok(){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message","操作成功");
        responseBo.put("code",200);
        return responseBo;
    }

    public static ResponseBo result(Object rs){
        return ok().put("data",rs);
    }

    public static ResponseBo fail(){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message","操作失败");
        responseBo.put("code",500);
        return responseBo;
    }
    public static ResponseBo fail(String msg){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message",msg);
        responseBo.put("code",502);
        return responseBo;
    }
    public static ResponseBo stack(String stack){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("stack",stack);
        responseBo.put("message","操作失败");
        responseBo.put("code",502);
        return responseBo;
    }

    public static ResponseBo warnMsg(String msg){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message","操作异常:"+msg);
        responseBo.put("code",501);
        return responseBo;
    }

    public static ResponseBo warnData(Object data){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message","操作异常");
        responseBo.put("code",501);
        responseBo.put("data",data);
        return responseBo;
    }


    public static ResponseBo message(String msg, Integer code){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message",msg);
        responseBo.put("code",code);
        return responseBo;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }

    public Integer getCode() {
        return Integer.valueOf(get("code").toString());
    }

    public static ResponseBo cus(Integer value, String msg) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("message",msg);
        responseBo.put("code",value);
        return responseBo;
    }
}
