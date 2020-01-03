package edu.hope.biz.common.resonse;

import com.alibaba.fastjson.annotation.JSONField;
import edu.hope.biz.common.constants.RStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author fanwenhao
 * @date 2019/9/28 10:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息
     */
    private String msg = RStatus.SUCCESS.getMessage();

    /**
     * 状态码
     */
    private int code = RStatus.SUCCESS.getValue();

    /**
     * 数据
     */
    private T data;

    public R(RStatus status) {
        this.msg = status.getMessage();
        this.code = status.getValue();
    }

    public R(RStatus status, String msg) {
        this.msg = msg;
        this.code = status.getValue();
    }

    public R(RStatus status, T data) {
        this.msg = status.getMessage();
        this.code = status.getValue();
        this.data = data;
    }

    public R(RStatus status, String msg, T data) {
        this.msg = msg;
        this.code = status.getValue();
        this.data = data;
    }

    public R(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public R(T data) {

        this.data = data;
    }

    public R(String msg) {
        this.msg = msg;
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> code(int code) {
        this.code = code;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }
    @JSONField(serialize = false)

//    public boolean isFailed() {
//        return RStatus.SUCCESS.getValue().equals(code);
//    }

    public static R ok(){
        return new R();
    }

    public static R ok(String msg){
        return new R(msg);
    }

    public  static  R ok(Object obj){
        return new R(obj);
    }

    public static R fail(String msg){
        return new R(msg);
    }

    public static R fail(RStatus rStatus,String msg){
        return new R(rStatus,msg);
    }

    public static R fail(Exception ex){
        return R.fail(ex.getMessage());
    }

}
