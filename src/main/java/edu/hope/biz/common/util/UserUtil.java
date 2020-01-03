package edu.hope.biz.common.util;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hope.biz.common.constants.RStatus;
import edu.hope.biz.common.exception.ServiceException;
import edu.hope.biz.entity.edit.UserCache;
import edu.hope.biz.entity.model.Users;
import edu.hope.biz.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：zhao
 * @description：
 * @date ：2019/12/31 0031 0:28
 */
public class UserUtil {

    @Autowired
    private UsersService usersService;

    public static final String Token = "token";

    private static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes requestAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttrs.getRequest();
    }

    /**
     * 获取当前登录用户的token
     *
     * @return
     */
    public static String getToken() {
        HttpServletRequest request = getHttpServletRequest();
        // 找出token
        String token = request.getHeader(UserUtil.Token);

        if (StrUtil.isBlank(token)) {
            token = request.getParameter(UserUtil.Token);
        }
        return token;
    }

    public  UserCache getLoginUser(){
        String token = getToken();

        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getIsDeleted,0).eq(Users::getToken,token);
        Users users = usersService.getOne(queryWrapper);
        if (BeanUtil.isEmpty(users)){
            throw new ServiceException(RStatus.NO_LOGIN,"当前用户未登录");
        }
        UserCache userCache = new UserCache();
        BeanUtils.copyProperties(users,userCache);
        return userCache;
    }
}
