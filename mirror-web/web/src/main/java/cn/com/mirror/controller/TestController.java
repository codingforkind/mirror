package cn.com.mirror.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("Rest APIs for test")
@RestController
@RequestMapping("/web/test")
public class TestController {

    @ApiOperation(value = "Test string")
    @RequestMapping(value = "/string/{test}", method = RequestMethod.GET)
    public String testString(@PathVariable String test) {
        System.out.println("hello " + test);
        return "HELLO -> " + test;
    }

    @ApiOperation(value = "Test free")
    @RequestMapping(value = "/free/{free}", method = RequestMethod.GET)
    public String testFree(@PathVariable String free) {
        return "FREE -> " + free;
    }

}
