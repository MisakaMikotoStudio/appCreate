package com.misaka.yuban.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 通过在启动参数中设置env的方式，获取当前所处环境
 */
public class EnvUtils {

    /**
     * 当前所处环境：test - 测试，prod - 生产
     */
    private static String env = null;

    /**
     * 获取当前环境
     * @return
     */
    public static String getEnv() {
        if (StringUtils.isBlank(env)) {
            String systemEnv = System.getProperty("spring.profiles.active");
            env = StringUtils.isBlank(systemEnv) ? "test" : systemEnv;
        }
        return env;
    }

    /**
     * 判断当前环境是否是生产
     * @return
     */
    public static Boolean isProd() {
        return "prod".equals(getEnv());
    }
}
