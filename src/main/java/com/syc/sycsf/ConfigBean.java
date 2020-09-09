package com.syc.sycsf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 上分配置，适用任何平台
 */
@Component
@ConfigurationProperties
public class ConfigBean {
    private String mainUrl;
    private String user;
    private String password;
    private String sfUrl;
    private String cookName;
}
