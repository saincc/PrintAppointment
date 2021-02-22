package com.print.print.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    private int id;

    private int uid;

    private int sid;

    private String shopname;

    private String address;

    private String support;

    private Double credit;

    private Integer sales;

    private String avatar;
}
