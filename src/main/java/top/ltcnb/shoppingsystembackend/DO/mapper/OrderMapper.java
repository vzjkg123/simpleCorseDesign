package top.ltcnb.shoppingsystembackend.DO.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.ltcnb.shoppingsystembackend.DO.entity.Order;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
