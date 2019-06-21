package com.cdyn.login.service.impl;

import com.cdyn.login.entity.User;
import com.cdyn.login.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 13:45
 */
@Component
public class UserServiceImpl implements UserService {

    Map<String,User> dao=new ConcurrentHashMap<>();

    {
        User user=new User();
        user.setId("1");
        user.setUserName("root");
        user.setPassword("root");
        dao.put("rootroot",user);
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }

    @Override
    public User findByUsername(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        User user1 = dao.get(userName + password);

        return user1;
    }
}
