package edu.hope.biz.common.constants;

import lombok.Getter;

/**
 * @author fanwenhao
 * @date 2019/9/28 10:09
 */
@Getter
public enum RStatus {
    SUCCESS(200, "成功"),
    FAIL(210, "失败"),

    NO_LOGIN(401, "请先登录"),
    LOGIN_EXPIRE(431,"登录失效,请重新登录"),
    NO_PERMISSION(432, "没有权限"),
    USER_FROZEN(433,"用户已冻结，请联系管理员"),
    WEIXIN_ERROR(801,"获取微信信息失败"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    PARAM_ERROR(602,"参数校验错误"),
	BUSINESS_PARAM_ERROR(603,"业务参数校验错误"),
    OPERATION_OUTTIME(604,"操作超时"),
    EXPRESSAGE_FAIL(605,"快递查询失败"),
    ACCOUNT_SHORTAGE(901,"账户余额不足，请先充值"),
    WXPAY_CREATE_ERROR(902,"生成预支付订单失败"),
    SEND_MSG_ERROR(1001,"发短信失败");

    private Integer value;


    private String message;

    RStatus(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

}
