package micro.service.entity;

import lombok.Value;

import java.math.BigInteger;

@Value
public class Dept {

    private BigInteger dept_id;
    private String dept_name;
    private String dept_introduce;
    private String dept_add;
    private String dept_head;
    private BigInteger dept_tel;

}
