package top.ltcnb.shoppingsystembackend.DO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author tiancai
 */
@Data
@AllArgsConstructor
public class UserInfo {
    @TableId(type = IdType.AUTO)
    Long id;
    String username;
    String password;
    Byte gender;
    String email;
    String tel;
    Integer points;
    Integer level;
    Boolean isAdminister;
    Date date;
}
