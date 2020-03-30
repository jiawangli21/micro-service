package micro.service.entity;

import java.math.BigInteger;

public class Function {

      private BigInteger fun_id;

      private String fun_subsystem_name;

      private String  fun_name;

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
