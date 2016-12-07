package me.jcala.xmarket.data.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trade {
    private String id;//商品ID
    private String title;//商品名字
    private User author;//商品所属者
    private long price;
    private String desc;
    private String tagName;
    private String schoolName;//所属的学校名称
    private List<String> imgUrls;//商品图片
    private String createTime;//商品创建时间
    private String donateTime;//商品捐赠时间
    private int status;//商品状态。0:在售，1:售出,2:捐赠
    private boolean releaseCheck;//为了在发布时方便检查数据完整性，不存储在数据库中

}
