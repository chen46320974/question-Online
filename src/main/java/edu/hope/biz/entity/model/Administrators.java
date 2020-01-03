package edu.hope.biz.entity.model;

import edu.hope.biz.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Administrators extends BaseEntity<Administrators> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 学生系统登录用户名
     */
    private String username;

    /**
     * 学生系统登录密码
     */
    private String password;

    /**
     * 积分
     */
    private Integer points;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
