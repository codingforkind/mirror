package cn.com.mirror.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bind {
    String value();
}
