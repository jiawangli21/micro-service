package micro.service.entity;

import lombok.Value;

import java.math.BigInteger;
import java.util.Date;

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

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public String getUser_acc() {
        return user_acc;
    }

    public void setUser_acc(String user_acc) {
        this.user_acc = user_acc;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Date getUser_age() {
        return user_age;
    }

    public void setUser_age(Date user_age) {
        this.user_age = user_age;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_add() {
        return user_add;
    }

    public void setUser_add(String user_add) {
        this.user_add = user_add;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
