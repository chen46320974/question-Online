package edu.hope.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hope.biz.entity.edit.UserCache;
import edu.hope.biz.entity.model.Users;
import edu.hope.biz.entity.request.UserRequest;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
public interface UsersService extends IService<Users> {
    
    /**
    *
    * @param: 
    * @author: zhaoMaoJie
    * @Date: 23:56 2019/12/29 0029
     */
    void register(UserRequest request);

    /**
    * 用户登录
    * @param: request
    * @author: zhao
    * @Date: 0:20 2019/12/30 0030
     */
    Users login(UserRequest request);

    /**
    * 获取当前登录用户
    * @param:
    * @author: zhao
    * @Date: 16:09 2020/1/1 0001
     */
    UserCache getLoginUser();


}
