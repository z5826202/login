package com.cdyn.login.handle;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 15:14
 */
public interface ILoginHandle extends Comparable<ILoginHandle> {

    /**
     * 是否忽略当前实现：true表示忽略；false表示不忽略,默认不忽略
     *
     */
    Boolean getIgnore();

    /**
     * 设置是否忽略当前实现：true表示忽略；false表示不忽略,默认不忽略

     */
    void setIgnore(Boolean ignore);

    /**
     * 获取 处理成功后是否继续下一步登录操作（true表示继续，false表示不继续，默认：继续）

     */
    Boolean getIsContinueForSuccess();

    /**
     * 设置 处理成功后是否继续下一步登录操作（true表示继续，false表示不继续，默认：继续）
     */
    void setIsContinueForSuccess(Boolean isContinueForSuccess);

    /**
     * 获取 处理失败后是否继续下一步登录操作（true表示继续，false表示不继续，默认：false不继续）
     */
    Boolean getIsContinueForFail();

    /**
     * 设置 处理失败后是否继续下一步登录操作（true表示继续，false表示不继续，默认：false不继续）
     *
     */
    void setIsContinueForFail(Boolean isContinueForFail);

    /**
     * 优先级(越小，优先级越高)（处理的先后顺序，优先级越高越先处理（值越小，越先处理）），默认0
     */
    Integer getOrder();

    /**
     * 设置 优先级(越小，优先级越高)（处理的先后顺序，优先级越高越先处理（值越小，越先处理）），默认0
     */
    void setOrder(Integer order);

    /**
     * 获取 登录处理结果
     *
     */
    Map<String,Object> getLoginHandleResultDto();

    /**
     * 设置 登录处理结果
     */
    void setLoginHandleResultDto(Map<String,Object> attachment);

    /**
     * 登录处理
     */
    void handle(HttpServletRequest request, HttpServletResponse response,Map<String,Object> attachment)
            throws ServletException, IOException, SQLException;

    /**
     * 登录处理成功后执行
     *

     */
    void onSuccess(HttpServletRequest request, HttpServletResponse response,Map<String,Object> attachment)
            throws ServletException, IOException, SQLException;

    /**
     * 登录处理成功后执行
     *
     */
    void onFail(HttpServletRequest request, HttpServletResponse response,Map<String,Object> attachment)
            throws ServletException, IOException, SQLException;

}
