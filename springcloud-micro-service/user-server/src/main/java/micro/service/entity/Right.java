package micro.service.entity;

import java.math.BigInteger;

public class Right {

    private BigInteger right_id;

    private String right_name;

    private int right_type;


    public BigInteger getRight_id() {
        return right_id;
    }

    public void setRight_id(BigInteger right_id) {
        this.right_id = right_id;
    }

    public String getRight_name() {
        return right_name;
    }

    public void setRight_name(String right_name) {
        this.right_name = right_name;
    }

    public int getRight_type() {
        return right_type;
    }

    public void setRight_type(int right_type) {
        this.right_type = right_type;
    }
}
