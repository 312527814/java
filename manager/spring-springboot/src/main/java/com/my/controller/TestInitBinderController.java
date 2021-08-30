package com.my.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-27 16:01
 */
@Controller
public class TestInitBinderController {
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @RequestMapping(value = "/param2", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFormatData(Date date) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "zhangsan");
        map.put("age", 22);
        map.put("date", date);

        return map;
    }

//    @InitBinder("user")
//    public void init1(WebDataBinder binder) {
//        binder.setFieldDefaultPrefix("u.");
//    }
//
    @InitBinder("stu")
    public void init2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("s.");
    }

    @ResponseBody
    @RequestMapping("/testBean")
    public String testBean( @ModelAttribute("stu")Student stu) {
        System.out.println(stu);
        String viewName = "success";
        return viewName;

    }

    public static   class Student {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
