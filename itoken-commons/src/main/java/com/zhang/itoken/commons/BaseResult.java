package com.zhang.itoken.commons;

import com.zhang.itokencommondomain.domain.TbSysUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 张宏运
 * @Date: 2019-05-15 13:43
 */
@Data
public class BaseResult implements Serializable {
    public static final String RESULT_OK = "ok";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String SUCCESS = "成功操作";
/**
 * @Author 张宏运
 * @Date  2019-05-15 14:02
 * result 仅包含ok 和not_ok 返回结果与状态码无关，
 * 仅仅表明了接口是否按请求方的预期完成
 */
    private String result;
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

   private static BaseResult createResult(String result, TbSysUser data, String success, Cursor cursor, List<Error> errors){
      BaseResult baseResult = new BaseResult();
      baseResult.setResult(result);
      baseResult.setData(data);
      baseResult.setSuccess(success);
      baseResult.setCursor(cursor);
      baseResult.setErrors(errors);
       return baseResult;

   }
    public static BaseResult ok(){
        return createResult(RESULT_NOT_OK,null,SUCCESS,null,null);
    }
    public static BaseResult ok(TbSysUser data){
       return createResult(RESULT_NOT_OK,data,SUCCESS,null,null);
    }
    public static BaseResult notOk(List<BaseResult.Error> errors){
       return createResult(RESULT_NOT_OK,null,"",null,errors);
    }



/**
 * @Author 张宏运
 * @Date  2019-05-15 14:01
 * total 全部条数
 * offset 当前所在位置
 * limit 每页条数
 */
    @Data
    public static class Cursor{
        private int total;
        private int offset;
        private int limit;
    }
    /**
     * @Author 张宏运
     * @Date  2019-05-15 13:58
     * 当客户端向接口的请求失败，errors用来返回具体错误信息，
     * errors包含了一个数组，每一个数组包括了一个具体的错误对象
     * 每一个错误对象包含了“错误信息”message 和错误字段field.
     * 如果错误信息没有针对一个具体的错误字段，错误字段的赋值为空。
     */
    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String message;

    }

}
