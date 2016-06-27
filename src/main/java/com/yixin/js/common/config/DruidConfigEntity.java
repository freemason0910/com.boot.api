package com.yixin.js.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 16/3/28.
 */
@Component
@ConfigurationProperties(prefix = "datasource")
public class DruidConfigEntity {

    private String name;

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String maxActive;

    private String initialSize;

    private String maxWait;

    private String minIdle;

    private String timeBetweenEvictionRunsMillis;

    private String minEvictableIdleTimeMillis;


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getDriverClassName() {
        return driverClassName;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
}
