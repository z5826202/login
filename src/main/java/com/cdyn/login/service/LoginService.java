package com.cdyn.login.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 15:41
 */
public interface LoginService {
    public void login(HttpServletRequest request, HttpServletResponse response,Map<String,Object> attachment)
            throws ServletException, IOException, SQLException;

}
