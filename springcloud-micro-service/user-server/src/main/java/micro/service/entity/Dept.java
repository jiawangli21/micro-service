package micro.service.entity;

import java.math.BigInteger;

public class Dept {

    private BigInteger dept_id;
    private String dept_name;
    private String dept_introduce;
    private String dept_add;
    private String dept_head;
    private BigInteger dept_tel;

    public BigInteger getDept_id() {
        return dept_id;
    }

    public void setDept_id(BigInteger dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_introduce() {
        return dept_introduce;
    }

    public void setDept_introduce(String dept_introduce) {
        this.dept_introduce = dept_introduce;
    }

    public String getDept_add() {
        return dept_add;
    }

    public void setDept_add(String dept_add) {
        this.dept_add = dept_add;
    }

    public String getDept_head() {
        return dept_head;
    }

    public void setDept_head(String dept_head) {
        this.dept_head = dept_head;
    }

    public BigInteger getDept_tel() {
        return dept_tel;
    }

    public void setDept_tel(BigInteger dept_tel) {
        this.dept_tel = dept_tel;
    }
}
