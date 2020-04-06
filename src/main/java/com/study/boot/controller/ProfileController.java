package com.study.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.boot.mapper.QuestionMapper;
import com.study.boot.model.QuestionUser;
import com.study.boot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Resource
    private QuestionMapper questionMapper;
    @GetMapping("profile/{action}")
    public String profile(@PathVariable("action") String action,
                          @RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          HttpServletRequest request,
                          Model model){
        if("questions".contains(action)){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName","我的问题");
        }
        if("replies".contains(action)){
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName","我的回复");
        }
        User user = (User)request.getSession().getAttribute("user");
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<QuestionUser> pageInfo = new PageInfo(questionMapper.listByUserId(Integer.parseInt(user.getAccountId())));
        model.addAttribute("pageInfoSelf",pageInfo);
        return "profile";
    }
}
