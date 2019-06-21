package com.cdyn.login.handle.impl;

import com.cdyn.login.entity.User;
import com.cdyn.login.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 17:00
 */
@Component
public class LoginHandleForSuccessImpl extends AbstractLoginHandle {
    public Integer getOrder() {
        super.setOrder(20);
        return super.getOrder();
    }

    /**
     * 获取token设置到请求头
     * @param request
     * @param response
     * @param attachment
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Map<String, Object> attachment) throws ServletException, IOException, SQLException {
        User user= (User) attachment.get("user");
        String jwt = JwtUtil.createJWT(1000 * 60 * 30, user);
        response.setHeader("token",jwt);
        attachment.put("token",jwt);


    }
}
