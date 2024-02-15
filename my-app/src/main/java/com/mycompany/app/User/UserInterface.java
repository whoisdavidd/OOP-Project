package com.mycompany.app.User;


import java.util.List;

public interface UserInterface {
    void SaveUser(UserEntity user);
    void UpdateUser(UserEntity user);
    UserEntity GetUserId(long id);
    List<UserEntity> GetAllUsers();
    void DeleteUser(long id);
}
