package cn.xnmll.demo_2020.mapper;

import cn.xnmll.demo_2020.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 22756 on 2020/12/19.
 */

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (TITLE,description,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
/*
create table QUESTION
(
	ID INT auto_increment,
	"description " TEXT,
	TITLE VARCHAR(50),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	CREATOR INT,
	COMMENT_COUNT INT default 0,
	LIKE_COUNT INT default 0,
	VIEW_COUNT INT default 0,
	TAG VARCHAR(256),
	constraint QUESTION_PK
		primary key (ID)
);

create table QUESTION
(
	ID INT auto_increment,
	"description " TEXT,
	TITLE VARCHAR(50),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	CREATOR INT,
	COMMENT_COUNT INT default 0,
	LIKE_COUNT INT default 0,
	VIEW_COUNT INT default 0,
	TAG VARCHAR(256),
	constraint QUESTION_PK
		primary key (ID)
);



 */
