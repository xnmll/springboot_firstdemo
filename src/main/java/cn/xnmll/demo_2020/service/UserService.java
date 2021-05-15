package cn.xnmll.demo_2020.service;

import cn.xnmll.demo_2020.mapper.UserMapper;
import cn.xnmll.demo_2020.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xnmll
 * @create 2021-05-2021/5/15  13:51
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            // insert
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
            // update
        }
    }
}
