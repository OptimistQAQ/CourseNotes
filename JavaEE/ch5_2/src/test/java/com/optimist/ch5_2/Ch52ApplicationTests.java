package com.optimist.ch5_2;

import com.optimist.ch5_2.pojo.Dog;
import com.optimist.ch5_2.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ch52ApplicationTests {

    @Autowired
    private Dog dog;

    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person.toString());
        System.out.println(dog.toString());
    }

}
