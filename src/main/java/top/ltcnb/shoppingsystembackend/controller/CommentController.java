package top.ltcnb.shoppingsystembackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.service.ICommentService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    final ICommentService commentService;

    @PostMapping("/postComment")
    public R<Object> postComment(HttpServletRequest request, @RequestParam String commodityId, @RequestParam String content) {
        return commentService.postComment(request, commodityId, content);
    }

    @GetMapping("/{commodityId}")
    public R<Object> getComments(@PathVariable String commodityId) {
        return commentService.listComments(commodityId);
    }

}
