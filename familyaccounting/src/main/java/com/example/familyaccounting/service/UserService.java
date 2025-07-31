package com.example.familyaccounting.service;

import com.example.familyaccounting.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    boolean register(User user);
    boolean updateUser(User user);
    boolean resetPassword(Integer userId);
    List<User> getFamilyMembers(Integer familyId);
    User getUserById(Integer id);
    boolean userExists(String username);
    List<User> getPendingMembers(Integer familyId);
    boolean processMemberApplication(Integer userId, Integer approverId, Integer familyId, boolean approve);
}
