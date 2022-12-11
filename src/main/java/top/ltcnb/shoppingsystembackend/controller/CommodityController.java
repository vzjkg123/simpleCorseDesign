package top.ltcnb.shoppingsystembackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.service.ICommodityService;

@RestController
@CrossOrigin
@RequestMapping("/commodity")
@RequiredArgsConstructor
public class CommodityController {
    final ICommodityService commodityService;

    @PostMapping("/upload")
    public R<Object> uploadPhoto(@RequestParam MultipartFile photo) {
        return commodityService.uploadCommodityPhoto(photo);
    }

    @GetMapping("/search/{keyWord}/{pageNum}")
    public R<Object> searchCommodity(@PathVariable("keyWord") String keyWord, @PathVariable("pageNum") Integer requestPage) {
        return commodityService.searchCommodity(keyWord, requestPage);
    }


    @GetMapping("/listSuggests/{pageNum}")
    public R<Object> listSuggests(@PathVariable("pageNum") Integer num) {
        return commodityService.listSuggestCommodity(num);
    }

    @GetMapping("/listRecommends")
    public R<Object> listRecommends() {
        return commodityService.listRecommendCommodity();
    }

}
