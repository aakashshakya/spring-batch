package com.spring.springbatch.batch;

import com.spring.springbatch.model.User;
import com.spring.springbatch.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DBWriter implements ItemWriter<User> {
    private final UserRepository userRepository;

    @Autowired
    public DBWriter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends User> users) throws Exception {
        log.info("Inserting value of user with data size: {}", users.size());
        userRepository.saveAll(users);
    }
}




