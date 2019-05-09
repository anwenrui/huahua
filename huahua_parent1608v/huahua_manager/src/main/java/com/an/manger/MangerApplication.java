package com.an.manger;

import huahua.common.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy
public class MangerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MangerApplication.class, args);
    }

  @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
  }
}