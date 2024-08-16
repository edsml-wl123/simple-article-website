package com.example.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    public interface Add extends Default {}  //校验分组
    public interface Update extends Default{}

    @NotNull(groups = Update.class)
    private Integer id;             // 主键ID

    @NotEmpty
    private String categoryName;    // 分类名称

    @NotEmpty
    private String categoryAlias;   // 分类别名

    private Integer createUser;     // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

//    public Integer getId() {
//        return id;
//    }

//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public String getCategoryAlias() {
//        return categoryAlias;
//    }
//
//    public Integer getCreateUser() {
//        return createUser;
//    }

}
