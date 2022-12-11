package top.ltcnb.shoppingsystembackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.entity.Commodity;

import javax.servlet.http.HttpServletRequest;

public interface ICommodityService extends IService<Commodity> {
    //move to IAdministerService
    //  R<Object> addCommodity(HttpRequest request, CommodityBody commodityBody);

    R<Object> uploadCommodityPhoto(MultipartFile photo);

    //move to IAdministerService
    //R<Object> deleteCommodity(HttpServletRequest request, String commodityId);

    R<Object> searchCommodity(String keyWord, Integer requestPage);

    R<Object> listRecommendCommodity();

    R<Object> listSuggestCommodity(Integer num);
}
