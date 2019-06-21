package com.cdyn.login.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhuolin
 * @date 2019/6/20 - 10:33
 */
@Data
public class User implements  Serializable {
    private static final long serialVersionUID = -6404827131097968504L;
    private  String id;
    private  String userName;
    private  String password;
}
