package com.example.springboot1.starter;

import com.example.springboot1.starter.clazz.Klass;
import com.example.springboot1.starter.clazz.School;
import com.example.springboot1.starter.clazz.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(AppConfiguration.class)
@ConditionalOnProperty(prefix = "springboot1.clazz", name = "enabled", havingValue = "true", matchIfMissing = true)
public class AppConfiguration {

    @Bean
    public Student student100() {
        return new Student().create();
    }

    @Bean
    public Klass getKlass() {
        Klass klass = new Klass();
        List<Student> list = new ArrayList<>();
        list.add(student100());
        klass.setStudents(list);
        return klass;
    }

    @Bean
    public School getSchool() {
        School school = new School();
        school.setClass1(getKlass());
        school.setStudent100(student100());
        return school;
    }

}
