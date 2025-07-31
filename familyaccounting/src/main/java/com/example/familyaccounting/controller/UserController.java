package com.example.familyaccounting.controller;

import com.example.familyaccounting.dto.FamilyDTO;
import com.example.familyaccounting.entity.Family;
import com.example.familyaccounting.entity.User;
import com.example.familyaccounting.service.FamilyService;
import com.example.familyaccounting.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 * 处理用户相关的所有操作：登录、注册、个人信息管理、家庭成员管理等
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FamilyService familyService;

    /**
     * 用户登录接口
     * @param user 包含用户名和密码的用户对象
     * @param request HTTP请求对象，用于获取会话
     * @return 登录结果，包含用户信息或错误消息
     */
    @PostMapping("/login")
    public ResponseResult<User> login(@RequestBody User user, HttpServletRequest request) {
        // 1. 调用服务层验证用户凭证
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());

        if (loggedInUser != null) {
            // 检查账号状态
            if ("PENDING".equals(loggedInUser.getStatus())) {
                return ResponseResult.error("账号正在审核中，请等待管理员批准");
            }
            if ("REJECTED".equals(loggedInUser.getStatus())) {
                return ResponseResult.error("账号申请已被拒绝，请联系管理员");
            }

            // 2. 防止会话固定攻击 - 使旧会话失效
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // 3. 创建新会话并存储用户信息
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", loggedInUser);

            // 4. 返回登录成功响应
            return ResponseResult.success(loggedInUser);
        }

        // 5. 登录失败返回错误信息
        return ResponseResult.error("用户名或密码错误");
    }

    /**
     * 用户注册接口
     * @param user 包含注册信息的用户对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResponseResult<Boolean> register(@RequestBody User user) {
        // 1. 验证家庭ID是否存在
        if (user.getFamilyId() == null || !familyService.existsById(user.getFamilyId())) {
            return ResponseResult.error("家庭ID不存在");
        }

        // 2. 检查用户名是否已存在
        if (userService.userExists(user.getUsername())) {
            return ResponseResult.error("用户名已存在");
        }

        // 3. 设置默认状态为待审核，角色为普通成员
        user.setStatus("PENDING");
        user.setRole("MEMBER");

        // 4. 调用服务层注册用户
        if (userService.register(user)) {
            return ResponseResult.success(true);
        }
        return ResponseResult.error("注册失败");
    }

    /**
     * 获取待审核用户列表（仅家庭管理员可用）
     * @param request HTTP请求对象
     * @return 待审核用户列表
     */
    @GetMapping("/pending-members")
    public ResponseResult<List<User>> getPendingMembers(HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 检查管理员权限
        if (!"ADMIN".equals(currentUser.getRole())) {
            return ResponseResult.error("只有家庭主管可以查看待审核成员");
        }

        // 3. 获取并返回待审核成员列表
        List<User> pendingMembers = userService.getPendingMembers(currentUser.getFamilyId());
        return ResponseResult.success(pendingMembers);
    }

    /**
     * 审核用户申请（仅家庭管理员可用）
     * @param userId 用户ID
     * @param approve 是否批准
     * @param request HTTP请求对象
     * @return 审核结果
     */
    @PostMapping("/approve-member/{userId}")
    public ResponseResult<Boolean> approveMember(
            @PathVariable Integer userId,
            @RequestParam Boolean approve,
            HttpServletRequest request) {

        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 检查管理员权限
        if (!"ADMIN".equals(currentUser.getRole())) {
            return ResponseResult.error("只有家庭主管可以审核成员");
        }

        // 3. 调用服务层处理审核
        if (userService.processMemberApplication(userId, currentUser.getId(), currentUser.getFamilyId(), approve)) {
            return ResponseResult.success(true);
        }
        return ResponseResult.error("审核失败");
    }

    /**
     * 更新用户信息
     * @param user 包含更新信息的用户对象
     * @param request HTTP请求对象
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(@RequestBody User user, HttpServletRequest request) {
        // 1. 检查会话有效性
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 获取当前登录用户
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 3. 设置要更新用户的ID为当前用户ID
        user.setId(currentUser.getId());

        // 4. 调用服务层更新用户信息
        if (userService.updateUser(user)) {
            // 5. 更新成功后，同步更新会话中的用户信息
            currentUser.setNickname(user.getNickname());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                currentUser.setPassword(user.getPassword());
            }
            return ResponseResult.success(true);
        }
        return ResponseResult.error("更新失败");
    }

    /**
     * 获取家庭成员列表（仅管理员可用）
     * @param request HTTP请求对象
     * @return 家庭成员列表
     */
    @GetMapping("/family-members")
    public ResponseResult<List<User>> getFamilyMembers(HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 检查管理员权限
        if (!"ADMIN".equals(currentUser.getRole())) {
            return ResponseResult.error("只有家庭主管可以查看家庭成员");
        }

        // 3. 获取并返回家庭成员列表
        List<User> members = userService.getFamilyMembers(currentUser.getFamilyId());
        return ResponseResult.success(members);
    }

    /**
     * 重置成员密码（仅管理员可用）
     * @param userId 要重置密码的用户ID
     * @param request HTTP请求对象
     * @return 重置结果
     */
    @PostMapping("/reset-password/{userId}")
    public ResponseResult<Boolean> resetPassword(@PathVariable Integer userId, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 检查管理员权限
        if (!"ADMIN".equals(currentUser.getRole())) {
            return ResponseResult.error("只有家庭主管可以重置密码");
        }

        // 3. 调用服务层重置密码
        if (userService.resetPassword(userId)) {
            return ResponseResult.success(true);
        }
        return ResponseResult.error("重置密码失败");
    }

    /**
     * 获取当前登录用户信息
     * @param request HTTP请求对象
     * @return 当前用户信息
     */
    @GetMapping("/current")
    public ResponseResult<User> getCurrentUser(HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 返回当前用户信息
        return ResponseResult.success(currentUser);
    }

    /**
     * 用户注销接口
     * @param request HTTP请求对象
     * @return 注销结果
     */
    @PostMapping("/logout")
    public ResponseResult<Boolean> logout(HttpServletRequest request) {
        // 1. 获取并销毁当前会话
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseResult.success(true);
    }
    /**
     * 获取所有家庭列表（用于注册时选择）
     * @return 家庭列表
     */
    @GetMapping("/families")
    public ResponseResult<List<Family>> getAllFamilies() {
        return ResponseResult.success(familyService.getAllFamilies());
    }

    @PostMapping("/register-admin")
    @Transactional
    public ResponseResult<Boolean> registerAdmin(@RequestBody Map<String, Object> requestData) {
        try {
            // 解析请求数据
            String username = (String) requestData.get("username");
            String password = (String) requestData.get("password");
            String nickname = (String) requestData.get("nickname");
            Map<String, Object> familyMap = (Map<String, Object>) requestData.get("family");

            // 验证数据
            if (username == null || password == null || nickname == null || familyMap == null) {
                return ResponseResult.error("缺少必要参数");
            }

            // 检查用户名是否已存在
            if (userService.userExists(username)) {
                return ResponseResult.error("用户名已存在");
            }

            // 创建家庭
            FamilyDTO familyDTO = new FamilyDTO();
            familyDTO.setName((String) familyMap.get("name"));
            familyDTO.setDescription((String) familyMap.get("description"));

            Integer familyId = familyService.createFamily(familyDTO);
            if (familyId == null) {
                return ResponseResult.error("创建家庭失败");
            }

            // 创建主管账号
            User adminUser = new User();
            adminUser.setUsername(username);
            adminUser.setPassword(password);
            adminUser.setNickname(nickname);
            adminUser.setRole("ADMIN");
            adminUser.setFamilyId(familyId);
            adminUser.setStatus("APPROVED"); // 主管自动批准

            if (userService.register(adminUser)) {
                // 更新家庭的创建者ID
                familyService.updateFamilyCreator(familyId, adminUser.getId());
                return ResponseResult.success(true);
            }

            return ResponseResult.error("注册失败");
        } catch (Exception e) {
            log.error("主管注册失败: {}", e.getMessage(), e);
            return ResponseResult.error("注册过程中发生错误");
        }
    }
}
