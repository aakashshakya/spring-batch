package com.spring.springbatch.batch;

import com.spring.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        return user;
    }
}
