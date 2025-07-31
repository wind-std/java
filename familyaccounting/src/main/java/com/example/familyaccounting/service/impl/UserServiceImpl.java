package com.example.familyaccounting.service.impl;

import com.example.familyaccounting.entity.User;
import com.example.familyaccounting.mapper.UserMapper;
import com.example.familyaccounting.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        // 检查用户名是否已存在
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return false;
        }
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }

    @Override
    public boolean resetPassword(Integer userId) {
        return userMapper.resetPassword(userId) > 0;
    }

    @Override
    public List<User> getFamilyMembers(Integer familyId) {
        return userMapper.selectByFamilyId(familyId);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean userExists(String username) {
        return userMapper.selectByUsername(username) != null;
    }

    @Override
    public List<User> getPendingMembers(Integer familyId) {
        return userMapper.selectByFamilyIdAndStatus(familyId, "PENDING");
    }

    @Override
    public boolean processMemberApplication(Integer userId, Integer approverId, Integer familyId, boolean approve) {
        User user = userMapper.selectById(userId);
        if (user == null || !"PENDING".equals(user.getStatus()) ||
                !familyId.equals(user.getFamilyId())) {
            return false;
        }
        if (approve) {
            // 批准申请
            log.info("批准运行了");
            log.info(user.getUsername());
            user.setStatus("APPROVED");
            user.setFamilyId(familyId);
            return userMapper.update(user) > 0;
        } else {
            // 拒绝申请
            log.info("拒绝执行了");
            user.setStatus("REJECTED");
            return userMapper.update(user) > 0;
        }
    }
}
