package edu.hope.biz.common.exception;

import edu.hope.biz.common.constants.RStatus;
import edu.hope.biz.common.resonse.R;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务处理异常
 * service 通用
 *
 * @author lujing
 * @date 2018/12/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1L;
    
    
    private Object data;
    
    
    private int code = RStatus.FAIL.getValue();
    
    public ServiceException() {
        super("服务异常");
    }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }
    
    public ServiceException(String message, Object data) {
        super(message);
        this.data = data;
        if (data instanceof RStatus) {
            RStatus status = (RStatus) data;
            this.code = status.getValue();
            this.data = this.getStackTrace()[0].getClassName();
        }
    }
    
    public ServiceException(String message, Object data, int code) {
        super(message);
        this.data = data;
        this.code = code;
    }
    
    public ServiceException(R r) {
        super(r.getMsg());
        this.data = r.getData();
        this.code = r.getCode();
    }
    
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException(String message, Object data, Throwable cause) {
        super(message, cause);
        this.data = data;
    }
    
    public ServiceException(RStatus rStatus) {
        super(rStatus.getMessage());
        this.code = rStatus.getValue();
    }
    
    public ServiceException(RStatus rStatus, String message) {
    	super(message);
        this.code = rStatus.getValue();
    }
    
    
    @Override
    public String toString() {
        return "ServiceException{" +
                "data=" + data +
                ", code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
