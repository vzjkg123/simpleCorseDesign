package top.ltcnb.shoppingsystembackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.entity.Commodity;
import top.ltcnb.shoppingsystembackend.DO.mapper.CommodityMapper;
import top.ltcnb.shoppingsystembackend.service.ICommodityService;
import top.ltcnb.shoppingsystembackend.util.MinioUtil;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {
    final MinioUtil minioUtil;
    final CommodityMapper commodityMapper;


    @Override
    public R<Object> uploadCommodityPhoto(MultipartFile photo) {

        String photoAddress;
        if ((photoAddress = minioUtil.upload(photo)) != null) {
            HashMap<String, String> res = new HashMap<>();
            res.put("url", photoAddress);
            return R.success(res);
        }
        return null;
    }


    @Override
    public R<Object> searchCommodity(String keyWord, Integer requestPage) {
        List<Commodity> res;
        Page<Commodity> page = new Page<>(requestPage, 6);
        res = commodityMapper
                .selectPage(page, new LambdaQueryWrapper<Commodity>().like(Commodity::getCommodityName, keyWord))
                .getRecords();

        return R.success(res);
    }

    @Override
    public R<Object> listRecommendCommodity() {
        List<Commodity> listRes = list(new LambdaQueryWrapper<Commodity>().eq(Commodity::getIsRecommend, true));
        return R.success(listRes);
    }

    @Override
    public R<Object> listSuggestCommodity(Integer num) {
        Page<Commodity> page = new Page<>(num, 12);
        List<Commodity> records = commodityMapper.selectPage(page,
                        new LambdaQueryWrapper<Commodity>().eq(Commodity::getIsRecommend, false))
                .getRecords();
        return R.success(records);
    }
}
