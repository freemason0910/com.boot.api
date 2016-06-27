package com.yixin.js;


import com.yixin.js.configure.AbstractApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.net.UnknownHostException;

/**
 * The main Java based Spring configuration
 * <p/>
 * Extends the exiting XML based configuration
 * <p/>
 * //@EnableAutoConfiguration(exclude = TraceWebFilterAutoConfiguration.class)
 *
 * @author <a href="mailto:jean@eastcode.org">Jean Seurin</a>
 * @since 14/08/15 - 17:10
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan("com.boot.api")

//spring-boot1.3生效
@SpringBootApplication
@ServletComponentScan
//@ImportResource({"classpath:applicationContext-consumer.xml"}) //引用外部xml文件
public class Application extends AbstractApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        abstractMain(app, args);

    }
}
