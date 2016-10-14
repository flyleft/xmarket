package me.jcala.xmarket.mvp.sort;

import java.util.List;

import me.jcala.xmarket.data.entity.SortTag;

public interface SortTagView {
    void whenSuccess(List<SortTag> tags);
    void whenFail(String msg);
}
