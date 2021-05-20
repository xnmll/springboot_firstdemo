package cn.xnmll.demo_2020.dto;

import lombok.Data;

/**
 * @author xnmll
 * @create 2021-05-2021/5/20  20:11
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
