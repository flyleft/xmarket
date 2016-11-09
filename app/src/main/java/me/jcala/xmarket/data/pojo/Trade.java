package me.jcala.xmarket.data.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Trade {
    private String id;//商品ID
    private String title;//商品名字
    private User author;//商品所属者
    private float price;
    private String tagId;
    private String schoolName;//所属的学校名称
    private List<String> imgUrls;//商品图片
    private String createTime;//商品创建时间
    private String donateTime;//商品捐赠时间
    private int status;//商品状态。0:在售，1:售出,2:捐赠
    private List<User> waitTrades;//商品待交易者名单
    private User trade;//商品交易者

}
