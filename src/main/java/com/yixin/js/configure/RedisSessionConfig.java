package com.yixin.js.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by 201603090214 on 2016/6/22.
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
