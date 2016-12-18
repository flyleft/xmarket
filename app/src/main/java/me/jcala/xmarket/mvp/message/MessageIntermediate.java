package me.jcala.xmarket.mvp.message;

import lombok.Getter;
import lombok.Setter;

public enum MessageIntermediate {
    instance;
    @Setter @Getter
    private int num=0;
}
