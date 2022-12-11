package top.ltcnb.shoppingsystembackend.DO.dto;


import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class QueryCommodityBody {
    String id;//精确查找
    String commodityName;//模糊查找

    Integer requestPage;//请求的页号
}
