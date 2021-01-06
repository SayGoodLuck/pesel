package dev.konstantin.dao;

import dev.konstantin.entity.UserInfo;

public interface UserServiceDao {

    UserInfo findByEmail(String email);

    UserInfo findByPesel(String pesel);
}
