package edu.hope.biz.controller;


import edu.hope.biz.common.resonse.R;
import edu.hope.biz.entity.model.Users;
import edu.hope.biz.entity.request.UserRequest;
import edu.hope.biz.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Validated
@RestController
@Api(tags = {"用户接口"})
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R userRegister(@Validated @RequestBody UserRequest request){
        usersService.register(request);
        return R.ok();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R userLogin(@Validated @RequestBody  UserRequest request){
        Users users = usersService.login(request);
        return R.ok(users);
    }


}
