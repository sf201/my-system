package cn.sf201.core.utils;

import java.util.Map;

/**
 * Created by sf201 on 2017/8/17.
 */
public class MapUtil {
    public static String getStringValue(String key, Map<String, Object> map) {
        if (map.containsKey(key)&&map.get(key)!=null) {
                return map.get(key).toString();
        }
        return "";
    }

    public static byte getByteValue(String key, Map<String, Object> map) {
        if (map.containsKey(key)&&map.get(key)!=null) {
            return Byte.parseByte(map.get(key).toString());
        } else {
            return 0;
        }
    }

    public static Map<String, Object> getMapValue(String key, Map<String, Object> map) {
        if (map.containsKey(key)&&map.get(key)!=null) {
            return (Map<String, Object>) map.get(key);
        } else {
            return null;
        }
    }
}
