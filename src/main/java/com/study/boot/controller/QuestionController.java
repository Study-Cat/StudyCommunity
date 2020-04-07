package com.study.boot.controller;

import com.study.boot.dto.QuestionDTO;
import com.study.boot.mapper.QuestionMapper;
import com.study.boot.model.QuestionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class QuestionController {
    @Resource
    private QuestionMapper questionMapper;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model){
        QuestionUser questionUser = questionMapper.getById(id);
        model.addAttribute("question",questionUser);
        return "question";
    }
}
