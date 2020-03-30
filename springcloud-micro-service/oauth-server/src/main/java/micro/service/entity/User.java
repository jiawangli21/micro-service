package micro.service.entity;

import lombok.Value;

import java.math.BigInteger;
import java.util.Date;

@Value
public class User {
    private BigInteger user_id;
    private String user_acc;
    private String user_pwd;
    private String user_nickname;
    private String user_gender;
    private Date user_age;
    private String user_tel;
    private String user_email;
    private String user_add;
    private String user_position;
    private String note;


}
