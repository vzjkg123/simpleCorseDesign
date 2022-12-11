package top.ltcnb.shoppingsystembackend.DO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    Long id;
    Long commodityId;
    Long uid;
    Byte paymentType;
    String tel;
    String address;
    Byte transportType;
    Boolean isCheck;
    String append;
    Date date;
}
