package com.cdyn.login.service;

import com.cdyn.login.entity.User;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 13:44
 */
public interface UserService {

    User findUserById(String userId);

    User findByUsername(User user);
}
