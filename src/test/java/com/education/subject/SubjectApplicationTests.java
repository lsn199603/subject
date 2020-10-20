


package com.education.subject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.education.subject.mapper"})
public class SubjectApplicationTests {
    @Test
    public void run(){
        String str = "李书宁" ;
        System.out.println(str);
    }

}



