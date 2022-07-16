package com.cursoSpringboot.cursoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoSpringApplication {

    public static void main(String[] args) {
        boolean es = true;

        try {
            SpringApplication.run(CursoSpringApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}





