package cn.xnmll.demo_2020.model;

import lombok.Data;

/**
 * Created by 22756 on 2020/12/19.
 */

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
