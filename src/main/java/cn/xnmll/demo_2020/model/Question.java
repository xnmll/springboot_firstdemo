package cn.xnmll.demo_2020.model;

import lombok.Data;

/**
 * Created by 22756 on 2020/12/19.
 */

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
