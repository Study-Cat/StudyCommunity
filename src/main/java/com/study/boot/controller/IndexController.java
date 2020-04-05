package com.study.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.boot.mapper.QuestionMapper;
import com.study.boot.mapper.UserMapper;
import com.study.boot.model.Question;
import com.study.boot.model.QuestionUser;
import com.study.boot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionMapper questionMapper;
    @GetMapping(value = {"/","/index"})
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(defaultValue = "1") int pageNum,
                        @RequestParam(defaultValue = "10") int pageSize){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<QuestionUser> pageInfo = new PageInfo(questionMapper.list());
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }
}
