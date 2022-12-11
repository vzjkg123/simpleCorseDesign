package top.ltcnb.shoppingsystembackend.DO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    Long id;
    Long uid;
    Long commodityId;
    String content;
    Date date;
}
