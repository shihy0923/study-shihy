package com.example.transaction.mapper;


import com.example.transaction.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {

    List<User> findAll();
}
