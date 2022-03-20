package com.example.javapatternmirea2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @Override
    public void run(String... args) throws Exception {
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        String content;
        if (file1.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            content = reader.readLine();
            reader.close();
            content = String.valueOf(content.hashCode());
        }
        else {
            content = "null";
        }
        Writer writer = new FileWriter(file2);
        writer.write(content);
        writer.close();
        System.out.println(content);
        System.out.println("running");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }
}
