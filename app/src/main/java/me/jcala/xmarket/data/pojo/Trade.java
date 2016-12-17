package me.jcala.xmarket.data.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trade{
    private String id;//商品ID
    private String title;//商品名字
    private User author;//商品所属者
    private long price;
    private String desc;
    private String tagName;
    private String schoolName;//所属的学校名称
    private List<String> imgUrls;//商品图片
    private long createTime;//商品创建时间
    private int status;//商品状态。0:在售，1:售出,2:捐赠
    @Expose
    private boolean releaseCheck;//为了在发布时方便检查数据完整性，不存储在数据库中
}
