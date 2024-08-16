package com.example.pojo;

import com.example.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {

    @NotNull(groups = Update.class)
    private Integer id;             // 主键ID

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\S{4,100}$")
    private String title;           // 文章标题

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\S{50,1000}$")
    private String content;         // 文章内容

    @URL
    private String coverImg;        // 封面图像

    @NotNull
    @NotEmpty
    @State
    private String state;           // 发布状态 已发布|草稿

    @NotNull
    private Integer categoryId;     // 文章分类ID

    private Integer createUser;     // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    public interface Add extends Default {}
    public interface Update extends Default {}

}
