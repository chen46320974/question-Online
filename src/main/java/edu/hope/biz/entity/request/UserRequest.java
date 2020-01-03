package edu.hope.biz.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：zhao
 * @description：
 * @date ：2019/12/29 0029 23:57
 */
@Data
@ApiModel(value = "用户登录/注册表单")
public class UserRequest {


    @ApiModelProperty(value = "学生系统登录用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;


    @ApiModelProperty(value = "学生系统登录密码")
    @NotBlank(message = "密码不能为空")
    private String password;


}
