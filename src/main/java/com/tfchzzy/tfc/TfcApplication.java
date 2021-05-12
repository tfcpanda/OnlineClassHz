package com.tfchzzy.tfc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com/tfchzzy/tfc/mapper")
@SpringBootApplication
public class TfcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfcApplication.class, args);
    }

}
