package com.cdyn.login.service.impl;

import com.cdyn.login.handle.ILoginHandle;
import com.cdyn.login.service.LoginService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 15:41
 */
@Component
public class LoginServiceImpl implements LoginService, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 登录处理实现集合
     */
    private static List<ILoginHandle> implList = new ArrayList<ILoginHandle>();

    public static List<ILoginHandle> getImplList() {
        return implList;
    }

    public static void setImplList(List<ILoginHandle> implList) {
        LoginServiceImpl.implList = implList;
    }


    @Override
    public void login(HttpServletRequest request, HttpServletResponse response,Map<String,Object> attachment) throws ServletException, IOException, SQLException {
        if (null != implList && implList.size() > 0) {
            for (int i = 0; null != implList && i < implList.size(); i++) {
                ILoginHandle impl = implList.get(i);
                if (null != impl) {
                    if (impl.getIgnore()) {// 忽略该登录实现
                        continue;
                    }
                    // 登录处理
                    impl.handle(request, response,attachment);
                    // 当前处理是否成功，true表示成功；false表示失败；默认成功
                    Boolean success = (Boolean) attachment.get("success");
                    if (success) {// 处理成功
                        impl.onSuccess(request, response,attachment);
                        // 处理成功后是否继续下一步登录操作（true表示继续，false表示不继续，默认：继续）
                        if (impl.getIsContinueForSuccess()) {// 继续
                        } else {// 不继续
                            break;
                        }
                    } else {// 处理失败
                        impl.onFail(request, response,attachment);
                        // 处理失败后是否继续下一步登录操作（true表示继续，false表示不继续，默认：false不继续）
                        if (impl.getIsContinueForFail()) {// 继续
                        } else {// 不继续
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, ILoginHandle> implMap=applicationContext.getBeansOfType(ILoginHandle.class);
        implList.addAll(implMap.values());
        Collections.sort(implList);
    }
}
