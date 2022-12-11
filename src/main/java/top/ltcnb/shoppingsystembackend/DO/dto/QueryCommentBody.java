package top.ltcnb.shoppingsystembackend.DO.dto;


import lombok.Data;

@Data
public class QueryCommentBody {
    String postId;
    String uid;
    String Content;

}
