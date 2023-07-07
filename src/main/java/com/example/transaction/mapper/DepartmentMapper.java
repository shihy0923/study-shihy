package com.example.transaction.mapper;


import com.example.transaction.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    
    List<Department> findAll();
    
    int update(Department department);
    
    Department findById(String id);
    
    int cleanCache();
}
