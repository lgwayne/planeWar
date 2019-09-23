package com.wayne.airplane.dao;

import com.wayne.airplane.entity.User;

import java.util.List;

public interface IUserDao {
    List<User> readAll();    // 读取所有的信息
    String getNewID();   // 返回最新主键数据
}
