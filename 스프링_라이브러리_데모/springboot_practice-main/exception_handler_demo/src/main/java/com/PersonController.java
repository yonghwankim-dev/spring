package com;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("/person")
public class PersonController {
    @GetMapping
    public String test(){
        return "test";
    }

    @GetMapping("/exception")
    public String exception1(){
        throw new NullPointerException();
    }

    @GetMapping("/exception2")
    public String exception2() throws IOException {
        throw new IOException();
    }

    @GetMapping("/exception3")
    public String exception3(){
        throw new IllegalStateException();
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String nullPointerExceptionHandle(NullPointerException ex){
        return "nullPointerException Handle!!";
    }

    @ExceptionHandler(value = {IOException.class, IllegalStateException.class})
    public String exception(Exception ex){
        return "exception handle!!";
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // 날짜 문자열을 localDate 형식으로 변환
    @GetMapping("/register")
    public String register(Person person){
        return person.toString();
    }

    //////////////////////////////////////////////////////////////////////////////////////

    // 컨트롤러내에서 공통으로 사용할 모델 정의
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg", "hello world");
    }

    @GetMapping("/page")
    public ModelAndView page(){
        ModelAndView mav = new ModelAndView("page");
        return mav;
    }
}
