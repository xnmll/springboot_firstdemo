package cn.xnmll.demo_2020.controller;

import cn.xnmll.demo_2020.dto.QuestionDTO;
import cn.xnmll.demo_2020.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xnmll
 * @create 2021-04-2021/4/21  20:48
 */

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name ="id") Integer id
        , Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
