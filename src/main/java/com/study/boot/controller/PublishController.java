package com.study.boot.controller;

import com.study.boot.mapper.QuestionMapper;
import com.study.boot.model.Question;
import com.study.boot.model.QuestionUser;
import com.study.boot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Resource
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String toUpdate(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
       QuestionUser questionUser = questionMapper.getById(id);
       User user = (User) request.getSession().getAttribute("user");
       //判断传过来的id是否是所登陆用的问题id，避免修改其他用户的问题id
       if (questionUser.getUser() !=null){
           if(user.getAccountId() == questionUser.getUser().getAccountId()){
               model.addAttribute("title",questionUser.getTitle());
               model.addAttribute("description",questionUser.getDescription());
               model.addAttribute("tag",questionUser.getTag());
               model.addAttribute("id",id);
           }
       }else{
           model.addAttribute("msg","查询不合法！");
           return "/error/error";
       }
       return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        if(title == null || description ==null || tag==null || title =="" || description == "" || tag ==""){
            model.addAttribute("error","标题、内容、标签不能为空！");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(Integer.parseInt(user.getAccountId()));
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        if(id == null){
            questionMapper.create(question);
        }else {
            questionMapper.setByIdUpdate(question);
        }
        return "redirect:/";
    }
}
