package com.itdfq.controller;

import com.itdfq.model.User;
import com.itdfq.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-10-22 16:41:28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    Map<String,Object> map = new HashMap<>();
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 检查用户是否登录
     * @param session
     * @return
     */
    @RequestMapping("/findByUser")
    public Map<String,Object> findByUser(HttpSession session){
        try {
            User user = (User) session.getAttribute("user");
            if (user==null){
                map.put("msg","用户登录异常，请重新登录");
                return map;
            }
            map.put("msg",1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody User user, HttpServletRequest request){
        map.clear();
        try {
            User login = userService.login(user);
            if (login == null) {
                map.put("msg", "用户名不存在！");
                return map;
            }
            if (!login.getPassword().equals(user.getPassword())) {
                map.put("msg", "用户名或密码错误！");
                return map;
            }
            map.put("msg", "1");
            request.getSession().setAttribute("user", login);
            map.put("role",login.getRole());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }
    // 退出系统
    @RequestMapping("/logout")
    public Map<String,Object> administratorLogout(HttpSession session) {
        map.clear();
        try {
            session.setAttribute("user", null);
            map.put("msg",1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
//        session.setAttribute("adminName",null);
        return map;
    }
}