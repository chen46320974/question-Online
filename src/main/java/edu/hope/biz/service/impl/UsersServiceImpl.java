package edu.hope.biz.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hope.biz.common.constants.RStatus;
import edu.hope.biz.common.exception.ServiceException;
import edu.hope.biz.common.util.CacheUtils;
import edu.hope.biz.common.util.UserUtil;
import edu.hope.biz.entity.edit.UserCache;
import edu.hope.biz.entity.model.Users;
import edu.hope.biz.entity.request.UserRequest;
import edu.hope.biz.mapper.UsersMapper;
import edu.hope.biz.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public void register(UserRequest request) {

        //首先判断用户是否有重名
        checkUserName(request.getUsername());


        //执行注册操作
        Users users = new Users();
        BeanUtil.copyProperties(request, users);

        //生成唯一随机token
        String token = IdUtil.simpleUUID();
        users.setToken(token);
        users.setGmtCreate(new Date());
        save(users);
    }

    @Override
    public Users login(UserRequest request) {

        //首先判断用户是否存在
        Users users = getUserByUserName(request.getUsername());
        if (BeanUtil.isEmpty(users)) {
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR, "当前用户尚未注册");
        }

        //不为空就将信息缓存 并返回users信息
//        UserCache userCache = new UserCache();
//        BeanUtils.copyProperties(users,userCache);
//        CacheUtils.put(users.getToken(),userCache);
//        CacheUtils.put(CacheUtils.USER_CACHE, users.getToken(),userCache);
//        Cache<String,Object> fifoCache = CacheUtil.newFIFOCache(3);
//        fifoCache.put(users.getToken(),userCache);
//        UserCache userCache1 = (UserCache) fifoCache.get(users.getToken());
        return users;
    }

    @Override
    public UserCache getLoginUser() {

        String token = UserUtil.getToken();

        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getIsDeleted, 0).eq(Users::getToken, token);
        Users users = getOne(queryWrapper);
        if (BeanUtil.isEmpty(users)) {
            throw new ServiceException(RStatus.NO_LOGIN, "当前用户未登录");
        }
        UserCache userCache = new UserCache();
        BeanUtils.copyProperties(users, userCache);
        return userCache;

    }

    /**
     * 检查用户名是否重复
     *
     * @param: userName
     * @author: zhao
     * @Date: 0:17 2019/12/30 0030
     */
    public void checkUserName(String userName) {
        Users users = getUserByUserName(userName);
        if (!BeanUtil.isEmpty(users)) {
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR, "当前用户名已被注册");
        }
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param: userName
     * @author: zhao
     * @Date: 0:22 2019/12/30 0030
     */
    public Users getUserByUserName(String userName) {
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getIsDeleted, 0).eq(Users::getUsername, userName);
        List<Users> usersList = list(queryWrapper);

        //不为空 返回用户信息
        if (!CollectionUtils.isEmpty(usersList)) {
            return usersList.get(0);
        }
        return null;
    }
}
