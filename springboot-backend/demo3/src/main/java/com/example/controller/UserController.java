package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.Jwt;
import com.example.utils.ThreadLocalUtil;
import com.example.utils.md5;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{4,10}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$")String password){
        User user=userService.findByName(username);
        if(user==null){
            userService.register(username,password);
            return Result.success();
        }
        else {
            return Result.error("Username already exists.");
        }
    }

    @PostMapping("/login")
    public Result login(String username,String password){
        User user=userService.findByName(username);
        if(user==null){
            return Result.error("Username incorrect.");
        }
        else if(Objects.equals(md5.MD5(password), user.getPassword())){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            claims.put("password",user.getPassword());
            return Result.success(Jwt.genToken(claims));
        }
        else {
            return Result.error("Password incorrect.");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        //根据用户名查询用户
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByName(username);
        return Result.success(user);
    }


    //@RequestBody属于springweb，从request请求体中获取json生成User对象
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        try{
            userService.update(user);
            return Result.success();
        }
        catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PatchMapping("/update_avatar")
    public Result update(@RequestParam @URL String userPic){
        try{
            userService.updateAvatar(userPic);
            return Result.success();
        }
        catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PatchMapping("/update_password")
    public Result updatePassword(@RequestBody Map<String,String> pws){
        String oldP=pws.get("old_pw");
        String newP=pws.get("new_pw");
        String reenterP=pws.get("rePw");

        Map<String,Object> map= ThreadLocalUtil.get();
        if(!Objects.equals(md5.MD5(oldP), map.get("password"))){
            //System.out.println(oldP);
            return Result.error("password incorrect.");
        }
        if(!Objects.equals(reenterP, newP)){
            return Result.error("re-entered password inconsistent with new password.");
        }

        try{
            userService.updatePassword(md5.MD5(newP),(Integer) map.get("id"));
            return Result.success();
        }
        catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}
