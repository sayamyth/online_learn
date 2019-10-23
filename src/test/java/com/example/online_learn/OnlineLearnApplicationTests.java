package com.example.online_learn;

import com.example.online_learn.entity.User;
import com.example.online_learn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineLearnApplicationTests {


    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
//        User user = new User();
//        user.setuName("111");
//        User user1 = userService.selectUserByName(user);

    }

}
