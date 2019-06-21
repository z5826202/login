package com.cdyn.login.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cdyn.login.annotation.CheckToken;
import com.cdyn.login.annotation.LoginToken;
import com.cdyn.login.entity.User;
import com.cdyn.login.service.LoginService;
import com.cdyn.login.service.UserService;
import com.cdyn.login.util.JwtUtil;


import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 13:54
 */
@RestController
@RequestMapping("/api")

public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PostMapping("/login")
    @LoginToken
    public Object login(@RequestBody @Valid User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> outMap=new HashMap<>();

        Map<String,Object> attachment=new ConcurrentHashMap<>();
        attachment.put("user",user);
        attachment.put("success",true);
        try {
            loginService.login(request,response,attachment);
        } catch (Exception e) {
            outMap.put("success",false);
            outMap.put("message",e.getMessage());
            outMap.put("token","");
            e.printStackTrace();
            return  outMap;
        }
        outMap.put("success",attachment.get("success"));
        outMap.put("message",attachment.get("message"));
        outMap.put("token",attachment.get("token"));
        return  outMap;
    }

    //查看个人信息
    @CheckToken
    @GetMapping("/getMessage")
    @ApiOperation(value = "检验用户登录", notes = "检验用户登录")
    public String getMessage(HttpServletRequest request) {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
//        DecodedJWT decode = JWT.decode(token);
        Claims claims = JwtUtil.parseJWT(token);
        return claims.toString();
    }


}