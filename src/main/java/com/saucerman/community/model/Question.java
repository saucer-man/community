package com.saucerman.community.model;
import lombok.Data;
@Data
public class Question {
    private Integer id; // id
    private String title; //标题
    private String description;  //描述
    private String tag; //标签
    private Long gmtCreate;  //创建时间
    private Long gmtModified;  // 修改时间
    private Integer creator;  // 创建的人
    private Integer viewCount; // 浏览数
    private Integer commentCount; // 评论数
    private Integer likeCount; // 点赞数


}
