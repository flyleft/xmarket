package me.jcala.xmarket.data.pojo;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class Team extends RealmObject{
    private String id;
    private String name;
    private String description;
    private String school;
    private String img;
    private String authorId;
    @Expose
    @Ignore
    private transient boolean releaseCheck;

    public Team() {
    }

    public Team(String name, String description, String img) {
        this.name=name;
        this.description=description;
        this.img=img;
    }
}
