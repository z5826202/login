package com.cdyn.login.handle.impl;

import com.cdyn.login.entity.User;
import com.cdyn.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 16:49
 */
@Component
public class LoginHandleForCheckPasswordImpl extends AbstractLoginHandle{

    public Integer getOrder() {
        // 步骤1
        super.setOrder(10);
        return super.getOrder();
    }
    @Autowired
    UserService userService;

    /**
     *验证账号密码
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Map<String, Object> attachment) throws ServletException, IOException, SQLException {
        User user = (User) attachment.get("user");
        User user1 = userService.findByUsername(user);

        if(null==user1){//未找到账号
            attachment.put("success",false);
            attachment.put("message","账号密码错误！");
        }else {
            attachment.put("user",user1);
        }

    }

}
