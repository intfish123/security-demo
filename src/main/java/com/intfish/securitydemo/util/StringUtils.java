package com.intfish.securitydemo.util;


import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**空字符串*/
    private static final String NULLSTR = "";

    /**
     * 获取参数不为空
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T nvl(T value,T defaultValue){
        return value !=null ? value : defaultValue;
    }

    /**
     * 判断一个对象是否为空
     * @param object
     * @return true为空 false为非空
     */
    public static boolean isNull(Object object){
        return object == null;
    }

    /**
     * 判断一个对象为非空
     * @param object
     * @return true:空  false：非空
     */
    public static boolean isNotNUll(Object object){
        return !isNull(object);
    }

    /**
     * 判断一个Collection是否为空，包含List,Set,Queue
     * @param coll
     * @return true:为空,false：非空
     */
    public static boolean isEmpty(Collection<?> coll){
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 判断一个Collection是否非空
     * @param coll
     * @return true:非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll){
        return !isEmpty(coll);
    }

    /**
     * 判断一个对象数组是否为空
     * @param objects
     * @return true:为空,false：非空
     */
    public static boolean isEmpty(Object[] objects){
        return isNull(objects) || objects.length==0;
    }

    /**
     * 判断一个对象数组为非空
     * @param objects
     * @return true:非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects){
        return !isEmpty(objects);
    }

    /**
     * 判断一个Map 是否为空
     * @param map
     * @return rue:为空,false：非空
     */
    public static boolean isEmpty(Map<?,?> map){
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断一个Map是否非空
     * @param map
     * @return true:非空 false：空
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }

    /**
     * 判断一个字符串是否为空
     * @param str
     * @return true:非空 false：空
     */
    public static boolean isEmpty(String str){
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * 判断一个字符串为非空
     * @param str
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean isEmpty(Object obj){
        return obj == null || "".equals(obj);
    }
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }

    /**
     * 判断一个对象是否是数组类型
     * @param object
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object){
        return isNotNUll(object) && object.getClass().isArray();
    }

    /**
     * 去字符串头尾空格
     * @param str
     * @return
     */
    public static String trim(String str){
        return str == null ? "" : str.trim();
    }

    /**
     * 截取字符串
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String substring(final String str,int start,int end){
        if (str == null){
            return NULLSTR;
        }
        if (end < 0){
            end = str.length() + end;
        }
        if (start < 0 ){
            start = str.length() + start;
        }
        if (end > str.length()){
            end = str.length();
        }
        if (start > end){
            return NULLSTR;
        }
        if (start < 0){
            start = 0;
        }
        if (end < 0){
            end = 0;
        }
        return str.substring(start,end);
    }


    /**
     * 判断是否包含字符串
     * @param str
     * @param strs
     * @return
     */
    public static boolean inStringIgnoreCase(String str,String... strs){
        if (str != null && strs != null){
            for(String s : strs){
                if (str.equalsIgnoreCase(trim(s))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将带下划线的字符串转换为驼峰形式 如 hello_world -> HelloWorld,HELLO_WORLD -> HelloWorld
     * @param name
     * @return
     */
    public static String convertToCamelCase(String name){
        StringBuilder result = new StringBuilder();
        //如果字符串为空，没必要转换
        if (name == null || isEmpty(name)){
            return "";
            //如果字符串不包含下划线，仅首字母大写
        }else if (!name.contains("_")){
            return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        }

        //使用“_”分割字符串
        String[] camels = name.split("_");
        for (String camel : camels){
            //跳过字符串开头中间结尾的下划线，以及双下划线
            if (camel.isEmpty()){
                continue;
            }
            //首字母大写
            result.append(camel.substring(0,1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 字符串数据处理
     * @param value
     * @return
     */
    public static String valueAsStr(Object value){
        if (value instanceof String){
            return (String) value;
        }
        if (value != null){
            return value.toString();
        }else {
            return null;
        }
    }

    /**
     * 整型数据处理
     */
    public static Integer valueAsInt(Object value){
        if (value instanceof Integer){
            return (Integer) value;
        }else if (value instanceof Number){
            return ((Number)value).intValue();
        }else if (value instanceof String){
            if ("NaN".equals(value)){
                return null;
            }
            return Integer.valueOf((String) value);
        }else if (value instanceof Boolean){
            return ((Boolean)value) ? 1 : 0;
        }
        else {
            return null;
        }
    }
}
