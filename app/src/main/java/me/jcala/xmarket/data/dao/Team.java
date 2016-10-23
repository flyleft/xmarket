package me.jcala.xmarket.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Team {
    private String id;
    private String name;
    private String description;
    private String img;

    public Team(String name,String description,String img) {
        this.name=name;
        this.description=description;
        this.img=img;
    }
}
