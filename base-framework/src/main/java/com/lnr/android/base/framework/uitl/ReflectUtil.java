package com.lnr.android.base.framework.uitl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author:lnr
 * date:2018/11/20
 * 反射工具类
 */
public class ReflectUtil {

    public static Class forName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static <T> T getFieldValue(Class cls, String fieldName, Object obj) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setField(Class cls, String fieldName, Object obj, Object value) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invoke(Class<?> cls, String fieldName, Class<?>[] params, Object obj, Object... values) {
        try {
            Method method = cls.getDeclaredMethod(fieldName, params);
            method.setAccessible(true);
            method.invoke(obj, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
