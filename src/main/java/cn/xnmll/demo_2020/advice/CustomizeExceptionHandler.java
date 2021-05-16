package cn.xnmll.demo_2020.advice;


import cn.xnmll.demo_2020.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xnmll
 * @create 2021-05-2021/5/16  20:05
 */

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {

        if (ex instanceof CustomizeException) {
            model.addAttribute("message",ex.getMessage());
        } else {
            model.addAttribute("message","稍后再来试试吧");
        }

        return new ModelAndView("error");

    }


}
