package com.asteroid.expression.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-09-28 11:40
 */
public class JsonUtil {

    public static JSONObject mapToJson(Map<String, Object> map) {
        JSONObject resultJson = new JSONObject();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            resultJson.put(key, map.get(key));
        }
        return resultJson;
    }

    public static JSONArray listMapToArray(List<Map<String, Object>> maps) {
        JSONArray resultArray = new JSONArray();
        for (Map<String, Object> map : maps) {
            resultArray.add(mapToJson(map));
        }
        return resultArray;
    }

}
