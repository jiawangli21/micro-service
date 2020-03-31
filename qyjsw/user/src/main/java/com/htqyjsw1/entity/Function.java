package com.htqyjsw1.entity;

import java.math.BigInteger;

/**
 * @desc 功能
 * @date 2020/3/30
 */
public class Function {

    private BigInteger fun_id;

    private String fun_subsystem_name;  //子系统名称

    private String fun_name; //功能名称

    public BigInteger getFun_id() {
        return fun_id;
    }

    public void setFun_id(BigInteger fun_id) {
        this.fun_id = fun_id;
    }

    public String getFun_subsystem_name() {
        return fun_subsystem_name;
    }

    public void setFun_subsystem_name(String fun_subsystem_name) {
        this.fun_subsystem_name = fun_subsystem_name;
    }

    public String getFun_name() {
        return fun_name;
    }

    public void setFun_name(String fun_name) {
        this.fun_name = fun_name;
    }
}
