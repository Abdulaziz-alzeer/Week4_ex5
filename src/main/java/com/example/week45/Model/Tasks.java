package com.example.week45.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tasks {

    private String ID;
    private String title ;
    private String description ;
    private boolean status ;
}
