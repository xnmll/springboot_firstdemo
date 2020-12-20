package cn.xnmll.demo_2020.mapper;

import cn.xnmll.demo_2020.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 22756 on 2020/12/19.
 */

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (TITLE,description,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
