package top.ltcnb.shoppingsystembackend.DO.dto;

import lombok.Data;

@Data
public class QueryUserBody {
    String username;
    String targetId;
    String email;
}
