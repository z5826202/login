package com.cdyn.login.handle.impl;

import com.cdyn.login.handle.ILoginHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 15:59
 */
public abstract class AbstractLoginHandle implements ILoginHandle {



    /**
     * 排序对比规则
     */
    public int compareTo(ILoginHandle o) {
        if (null != this && null != o && null != this.getOrder()
                && null != o.getOrder()) {
            return this.getOrder() - o.getOrder();
        }
        return 0;
    }

    /**
     * 是否忽略当前实现：true表示忽略；false表示不忽略,默认不忽略
     */
    private Boolean ignore = Boolean.FALSE;
    /**
     * 处理成功后是否继续下一步登录操作（true表示继续，false表示不继续，默认：继续）
     */
    private Boolean isContinueForSuccess = Boolean.TRUE;
    /**
     * 处理失败后是否继续下一步登录操作（true表示继续，false表示不继续，默认：false不继续）
     */
    private Boolean isContinueForFail = Boolean.FALSE;
    /**
     * 设置 优先级(越小，优先级越高)（处理的先后顺序，优先级越高越先处理（值越小，越先处理）），默认0
     */
    private Integer order = 0;
    /**
     * 登录处理结果
     */
    private Map<String,Object> attachment = new ConcurrentHashMap<>();

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    public Boolean getIsContinueForSuccess() {
        return isContinueForSuccess;
    }

    public void setIsContinueForSuccess(Boolean isContinueForSuccess) {
        this.isContinueForSuccess = isContinueForSuccess;
    }

    public Boolean getIsContinueForFail() {
        return isContinueForFail;
    }

    public void setIsContinueForFail(Boolean isContinueForFail) {
        this.isContinueForFail = isContinueForFail;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Map<String,Object>  getLoginHandleResultDto() {
        return attachment;
    }

    public void setLoginHandleResultDto(
            Map<String,Object> loginHandleResultDto) {
        this.attachment = loginHandleResultDto;
    }



    /**
     * 默认实现，
     */

    public void onSuccess(HttpServletRequest request,
                          HttpServletResponse response,Map<String, Object> attachment) throws ServletException, IOException,
            SQLException {
        // 设置页面显示消息

    }

    /**
     * 默认实现
     */

    public void onFail(HttpServletRequest request, HttpServletResponse response,Map<String, Object> attachment)
            throws ServletException, IOException, SQLException {

    }




}
