package top.ltcnb.shoppingsystembackend.DO.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import top.ltcnb.shoppingsystembackend.DO.entity.Commodity;

import java.math.BigDecimal;

@Data
public class CommodityBody {

    String commodityName;
    Integer amount;
    BigDecimal price;
    Byte type;
    String introduction;
    Boolean isRecommend;
    Integer score;
    String photo;

    public Commodity toCommodity() {
        return new Commodity(null, commodityName, amount, price, type, introduction, isRecommend, score, photo, null);
    }
}
