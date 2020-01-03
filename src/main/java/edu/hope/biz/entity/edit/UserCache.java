package edu.hope.biz.entity.edit;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author ：zhao
 * @description：
 * @date ：2019/12/31 0031 0:04
 */
@Data
@ApiModel(value = "用户缓存信息")
public class UserCache {

    /**
     *id
     */
    private Integer id;

    /**
     * 学生系统登录用户名
     */
    private String username;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 用户token
     */
    private String token;

}
