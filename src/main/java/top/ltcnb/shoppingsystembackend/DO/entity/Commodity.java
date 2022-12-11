package top.ltcnb.shoppingsystembackend.DO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @TableId(type = IdType.AUTO)
    Long id;
    String commodityName;
    Integer amount;
    BigDecimal price;
    Byte type;
    String introduction;
    Boolean isRecommend;
    Integer score;
    String photo;
    Date date;

}
