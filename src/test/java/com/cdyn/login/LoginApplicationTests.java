package com.cdyn.login;

import com.cdyn.login.entity.User;
import com.cdyn.login.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes= LoginApplication.class)
@ContextConfiguration(locations = {"classpath*:application.properties"})
public class LoginApplicationTests {

    @Test
    public void contextLoads() {
        User user=new User();
        user.setId("1");
        user.setUserName("xm");
        user.setPassword("123aaabbb");


//        String jwt = JwtUtil.createJWT(1000 * 60, user);
        String jwt = JwtUtil.createJWT(1, user);

        Claims claims = JwtUtil.parseJWT(jwt);
        System.out.println(claims);

    }

}
