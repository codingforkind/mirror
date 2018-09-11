package cn.com.mirror.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("Rest APIs for test")
@RestController
@RequestMapping("/web/test")
public class TestController {

    @ApiOperation(value = "Test string")
    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public String testString(@ApiParam(name = "name") String test) {
        System.out.println("hello " + test);
        return "HELLO -> " + test;
    }

    @ApiOperation(value = "Test free")
    @RequestMapping(value = "/free", method = RequestMethod.GET)
    public String testFree(@ApiParam(name = "free") String free) {
        return "FREE -> " + free;
    }

}
