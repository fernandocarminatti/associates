package com.associates.associates.utils;

import com.associates.associates.model.UserModel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static void copyNonNull(UserModel source, UserModel target) {
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

    public static boolean checkExistenceOfProperty(String[] userModelProperties, String[] properties) {
        Set<String> propertiesSet = new HashSet<>(Arrays.asList(properties));

        for(String property : userModelProperties) {
            if(!propertiesSet.contains(property)) {
                return false;
            }
        }
        return true;
    }
}
