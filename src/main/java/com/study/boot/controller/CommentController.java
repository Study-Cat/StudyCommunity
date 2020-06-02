package com.study.boot.controller;

import com.study.boot.dto.CommentCreateDTO;
import com.study.boot.dto.ResultDTO;
import com.study.boot.exception.CustomizeErrorCode;
import com.study.boot.mapper.CommentMapper;
import com.study.boot.model.Comment;
import com.study.boot.model.User;
import com.study.boot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Resource
    private CommentMapper commentMapper;
    @RequestMapping("/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator((long) 5);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
