package com.java.entity;

//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

@Data
public class Menu {
    private int menu_id;
    private String  menu_name;
    private Double  menu_price;
    private String  menu_type;
}
