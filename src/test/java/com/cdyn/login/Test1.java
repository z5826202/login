package com.cdyn.login;

import com.cdyn.login.entity.User;
import com.cdyn.login.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 11:02
 */
public class Test1 {
    @Test
    public void jwtUtil() {
        User user = new User();
        user.setId("1");
        user.setUserName("xm");
        user.setPassword("123aaabbb");


//        String jwt = JwtUtil.createJWT(1000 * 60, user);
        String jwt = JwtUtil.createJWT(1, user);

        Claims claims = JwtUtil.parseJWT(jwt);
        System.out.println(claims);

        final Boolean verify = JwtUtil.isVerify(jwt, user);
        System.out.println(verify);
    }

    @Test
    public void send123123() {
        String url = "http://localhost:8080/api/getMessage";
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290IiwicGFzc3dvcmQiOiJyb290IiwiaWQiOiIxIiwidXNlck5hbWUiOiJyb290IiwiZXhwIjoxNTYxMDg2NjkxLCJpYXQiOjE1NjEwODQ4OTEsImp0aSI6IjIwNTg3NjZiLWEwNmUtNDc0NC1hZDY5LTQxNTg0ZTU0ZmI1YSJ9.77Pn4_ekhDi-pZhDSAUaPZyvySVRb0glLd6VDGebIVw";
        CloseableHttpClient httpCilent = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("token",token );
        String respContent = "";
        try {
            httpCilent = HttpClients.createDefault();
//			org.apache.http.params.HttpParams params = new BasicHttpParams();
//	           params.setParameter("http.protocol.handle-redirects", false); // 默认不让重定向
//	           httpGet.setParams(params);
            HttpResponse response = httpCilent.execute(httpGet);
            Header[] headers2 = response.getAllHeaders();
            if (response.getStatusLine().getStatusCode() == 200) {
                respContent = EntityUtils.toString(response.getEntity());//获得返回的结果
                System.out.println("返回信息："+respContent);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
