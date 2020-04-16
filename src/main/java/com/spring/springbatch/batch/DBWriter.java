package com.spring.springbatch.batch;

import com.spring.springbatch.model.Department;
import com.spring.springbatch.model.User;
import com.spring.springbatch.repository.DepartmentRepository;
import com.spring.springbatch.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
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
    private JobExecution jobExecution;

    @Autowired
    public DBWriter(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void write(List<? extends User> users) {
        JobParameters parameters = jobExecution.getJobParameters();
        String departmentName =  parameters.getString("department");
        Department department = departmentRepository.findByName(departmentName);
        if(department != null){
            Department finalDepartment = department;
            users.forEach(user -> user.setDepartment(finalDepartment));
            isSetDepartment = true;
        } else {
            if(!isSetDepartment){
                department = departmentRepository.save(new Department(departmentName));
                Department finalDepartment1 = department;
                users.forEach(user -> user.setDepartment(finalDepartment1));
                isSetDepartment = true;
            } else {
                department = departmentRepository.save(new Department(departmentName));
                Department finalDepartment1 = department;
                users.forEach(user -> user.setDepartment(finalDepartment1));
            }
        }

        log.info("Inserting value of user with data size: {}", users.size());
        userRepository.saveAll(users);
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }
}




