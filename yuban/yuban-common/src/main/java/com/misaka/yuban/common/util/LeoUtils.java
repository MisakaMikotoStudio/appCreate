package com.misaka.yuban.common.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LeoUtils {

    /**
     * 从leo获取key对应的配置
     * @param key 配置的key
     * @return 如果配置不存在，则返回null
     */
    public static String getStringProperty(String key, String defaultValue) {
        String value = getStringProperty(key);
        return (value == null) ? defaultValue : defaultValue;
    }

    /**
     * 从leo获取key对应的配置
     * @param key 配置的key
     * @return 返回null，代表key不存在
     */
    public static String getStringProperty(String key) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("env", EnvUtils.getEnv());
        paramMap.put("project", "yuban");
        paramMap.put("key", key);
        String result = HttpUtil.get("http://1.15.139.117:8082/sdk/config/query", paramMap);
        JSONObject response = JSON.parseObject(result);
        if (response == null || response.getInteger("code") == null) {
            log.error("获取leo配置异常，返回response不正确:{}", response);
            throw new RuntimeException("获取leo配置异常：返回结果无法正确解析");
        }
        switch (response.getInteger("code")) {
            case 0:
                if (response.getJSONObject("data") == null) {
                    log.error("获取leo配置异常，返回data为空:{}", response);
                    throw new RuntimeException("获取leo配置异常：无法获取配置");
                }
                String value = response.getJSONObject("data").getString("value");
                if (value == null) {
                    log.error("获取leo配置异常，返回配置value为空:{}", response);
                    throw new RuntimeException("获取leo配置异常：无法获取配置");
                }
                return value;
            case 10000:
                throw new RuntimeException("获取leo配置异常：当前项目配置不正确-" + paramMap.get("project"));
            case 10001:
                throw new RuntimeException("获取leo配置异常：当前环境配置不正确-" + paramMap.get("env"));
            case 10002:
                log.warn("获取leo配置异常：配置的key不存在-" + key);
                return null;
            default:
                log.error("获取leo配置异常，参数:{}, 返回:{}", paramMap, response);
                throw new RuntimeException("获取leo配置异常");
        }
    }
}
