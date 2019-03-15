package cn.com.app.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2019/3/12.
 */
@Controller
@RequestMapping(path="test")
public class TestController {

    @RequestMapping(path="index.act",method = RequestMethod.GET)
    public String testMethod(HttpServletRequest request, HttpServletResponse response){
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");
        System.out.println("*****************************************");

        return "index";
    }
}
