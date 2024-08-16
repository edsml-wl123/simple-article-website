package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    /* Validation注解通常是在接收HTTP请求时对请求参数进行验证时起作用的，
    / 这些注解是 Java Bean Validation 中的一部分。 */
    @NotNull  //以Json格式获取时不能为Null
    private int id;

    @Pattern(regexp = "^\\S{4,10}$")
    private String username;

    @JsonIgnore  //以Json格式获取时忽略
    @Pattern(regexp = "^\\S{5,12}$")
    private String password;

    @NotNull
    @Pattern(regexp = "^\\S{4,12}$")
    private String nickname;

    @Email
    @NotEmpty
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", userPic='" + userPic + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    // Constructors
    public User() {}

    public User(int id, String username, String password, String nickname, String email, String userPic, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.userPic = userPic;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
