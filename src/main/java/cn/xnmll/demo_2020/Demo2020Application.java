package cn.xnmll.demo_2020;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xnmll.demo_2020.mapper")
public class Demo2020Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo2020Application.class, args);
    }

}






