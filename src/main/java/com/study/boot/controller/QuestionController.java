package com.study.boot.controller;

import com.study.boot.dto.CommentDTO;
import com.study.boot.dto.QuestionDTO;
import com.study.boot.exception.CustomizeErrorCode;
import com.study.boot.exception.CustomizeException;
import com.study.boot.mapper.QuestionMapper;
import com.study.boot.model.QuestionUser;
import com.study.boot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QuestionController {
    @Resource
    private QuestionMapper questionMapper;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id, Model model){
        QuestionUser questionUser = questionMapper.getById(id);
        List<CommentDTO> comments = commentService.listByQuestionId(id);
        if(questionUser == null){
          throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        questionMapper.incView(id);
        model.addAttribute("question",questionUser);
        return "question";
    }
}
