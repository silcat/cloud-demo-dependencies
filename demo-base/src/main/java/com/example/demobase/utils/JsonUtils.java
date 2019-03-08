package com.example.demobase.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demobase.core.ResultCode;

/**
 * Json工具类
 */
public class JsonUtils {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static JSONObject toJsonObject(String json) {
        return JSON.parseObject(json);
    }

    public static JSONObject initJSONObject(int code, String msg) {
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("msg", msg);
        return data;
    }

    public static JSONObject initJSONObject(ResultCode status) {
        return initJSONObject(status.getCode(), status.getMsg());
    }




}