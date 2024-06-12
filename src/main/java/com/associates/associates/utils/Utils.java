package com.associates.associates.utils;

import java.lang.reflect.Field;

public class Utils {

    public static void copyNonNull(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if(value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
