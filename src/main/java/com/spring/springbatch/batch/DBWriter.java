package com.spring.springbatch.batch;

import com.spring.springbatch.model.Department;
import com.spring.springbatch.model.User;
import com.spring.springbatch.repository.DepartmentRepository;
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
    private final DepartmentRepository departmentRepository;
    private static boolean isSetDepartment;
    private static Department department;

    @Autowired
    public DBWriter(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void write(List<? extends User> users) throws Exception {
        if(!isSetDepartment){
            department = departmentRepository.save(new Department("Development"));
            isSetDepartment = true;
        }
        users.forEach(user -> user.setDepartment(department));

        log.info("Inserting value of user with data size: {}", users.size());
        userRepository.saveAll(users);
    }
}




