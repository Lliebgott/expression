package com.asteroid.expression.common.config.security;

import com.asteroid.expression.sys.dao.UserDao;
import com.asteroid.expression.sys.entity.User;
import com.asteroid.expression.sys.sysenum.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author: YuSai
 * @date: 2020-10-21 17:22
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    public static User user;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        user = userDao.findByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 自定义错误的文章说明的地址：http://blog.csdn.net/z69183787/article/details/21190639?locationNum=1&fps=1
        if("0".equals(user.getStatus() + "")){
            throw new LockedException("用户账号被冻结，无法登陆请联系管理员！");
        }
        return user;
    }

    public static boolean onlyNormalUser() {
        if (user.getRoles().size() == 1 &&
                user.getRoles().get(0).getRoleName().equals(UserRoleEnum.NORMAL.getRoleName())) {
            return true;
        }
        return false;
    }

}
