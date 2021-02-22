package com.print.print.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;

    private int uid;

    private int sid;

    private int pid;

    private Date date;

    private String file;

    private String needs;

    private String other_needs;

    private String state;
}
