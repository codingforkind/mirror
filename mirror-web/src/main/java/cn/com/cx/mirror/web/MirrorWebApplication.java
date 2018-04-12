package cn.com.cx.mirror.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.com.cx.ps.mirror.Application;

/**
 * @author Piggy
 *
 * @description 
 * @since 2018年4月12日
 */
@SpringBootApplication
public class MirrorWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
