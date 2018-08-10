package cn.com.mirror.utils;

import cn.com.mirror.annotation.Bind;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class PropertyUtils {


    /**
     * Fill the object with props
     *
     * @param props  properties which are load from a specific "properties" file
     * @param object the object which needs to initialize the value of its property
     * @throws IllegalAccessException
     */
    public final static void mappingProperties(Properties props,
                                               Object object)
            throws IllegalAccessException {

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Bind.class)) {
                Bind bind = field.getAnnotation(Bind.class);
                String value = bind.value();
                String propValue = props.getProperty(value);
                field.setAccessible(true);
                field.set(object, propValue);
            }
        }
    }
}
