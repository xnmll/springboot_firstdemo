package cn.xnmll.demo_2020.mapper;

import cn.xnmll.demo_2020.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 22756 on 2020/12/19.
 */

@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified})")
    void insert(User user);
}
