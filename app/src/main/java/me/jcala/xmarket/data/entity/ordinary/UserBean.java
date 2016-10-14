package me.jcala.xmarket.data.entity.ordinary;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
    private String username;
    private String password;
    private String school;
    private String phone;
    private String avater_url;
    private List<String> item;
    private List<String> donation;
    private List<String> car;
    private List<String> history;
}
