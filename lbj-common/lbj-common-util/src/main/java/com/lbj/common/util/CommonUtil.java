package com.lbj.common.util;


import com.lbj.common.util.type.StringUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

    /**
     * 两数相除保留小数
     * @param in
     * @param pattern
     * @return
     */
    public static double doubleFormat(double in, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);// 格式化小数 例如"0.00"
        String out = df.format(in);// 返回的是String类型
        return Double.parseDouble(out);
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     */
    @SuppressWarnings({"rawtypes"})
    public static Map<String, Object> convertBeanToMap(Object bean) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            Class type = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    @SuppressWarnings("rawtypes")
    public static Object convertMapToBean(Class type, Map<String, Object> map) {
        BeanInfo beanInfo;
        Object obj = null;
        try {
            beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance(); // 创建 JavaBean 对象
            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // 获取类属性
        return obj;
    }

    /**
     * Map<Object, Object> 转 Map<String, Object>
     *
     * @param map
     * @return
     */
    public static Map<String, Object> convertMapKey(Map<Object, Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            result.put(String.valueOf(entry.getKey()),
                    String.valueOf(entry.getValue()));
        }
        return result;
    }


    public static String filterSql(String s) {
        if (StringUtil.isBlank(s)) {
            return s;
        }
        s = s.trim().toLowerCase();
        s = s.replace("=", "");
        s = s.replace("'", "");
        s = s.replace(";", "");
        s = s.replace(" or ", "");
        if (!(s.contains("insert_time") || s.contains("create_user") || s.contains("update_user") || s.contains("create_time") || s.contains("create_date")
                || s.contains("update_date") || s.contains("update_time"))) {
            s = s.replace("select", "");
            s = s.replace("update", "");
            s = s.replace("insert", "");
            s = s.replace("create", "");
        }

        s = s.replace("delete", "");
        s = s.replace("declare", "");
        s = s.replace("exec", "");
        s = s.replace("drop", "");
        s = s.replace("%", "");
        s = s.replace("--", "");
        return s;
    }
}
