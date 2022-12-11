package top.ltcnb.shoppingsystembackend.DO.dto;

import lombok.Data;
import top.ltcnb.shoppingsystembackend.DO.entity.UserInfo;

@Data
public class RegisterBody {
    String username;
    String email;
    String password;
    Byte gender;
    String tel;


    public UserInfo toUserInfo() {
        return new UserInfo(null, username, password, gender, email, tel, null, null, null, null);
    }
}
