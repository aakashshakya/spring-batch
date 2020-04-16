package com.spring.springbatch;

import com.spring.springbatch.model.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String name;
    private Integer salary;
    private String department;
    private Department departmentType;
}
