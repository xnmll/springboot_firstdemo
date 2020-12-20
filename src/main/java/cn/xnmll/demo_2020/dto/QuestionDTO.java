package cn.xnmll.demo_2020.dto;

import cn.xnmll.demo_2020.model.User;
import lombok.Data;

/**
 * Created by 22756 on 2020/12/20.
 */

@Data
public class QuestionDTO {
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
    private User user;
}
