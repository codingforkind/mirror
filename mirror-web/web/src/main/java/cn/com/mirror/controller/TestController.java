package cn.com.mirror.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/web/test")
public class TestController {


    @GetMapping(name = "/string")
//    @RequestMapping(name = "/string", method = RequestMethod.GET)
    public String testString() {
        System.out.println("TEST STRING");
        return "TEST STRING";
    }

}
