package com.cdyn.login.handle.impl;

import com.cdyn.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 17:37
 */
@Component
public class LoginHandleForRedis extends AbstractLoginHandle{
    public Integer getOrder() {
        super.setOrder(30);
        return super.getOrder();
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Map<String, Object> attachment) throws ServletException, IOException, SQLException {
        User user= (User) attachment.get("user");
        String id=user.getId();
        redisTemplate.opsForValue().set(id,user,30,TimeUnit.MINUTES);

        User user2 = (User) redisTemplate.opsForValue().get(id);
        System.out.println(user2);
    }
}
