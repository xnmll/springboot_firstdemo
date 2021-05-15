package cn.xnmll.demo_2020.service;

import cn.xnmll.demo_2020.dto.PaginationDTO;
import cn.xnmll.demo_2020.dto.QuestionDTO;
import cn.xnmll.demo_2020.mapper.QuestionMapper;
import cn.xnmll.demo_2020.mapper.UserMapper;
import cn.xnmll.demo_2020.model.Question;
import cn.xnmll.demo_2020.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22756 on 2020/12/20.
 */

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.count();


        if(totalCount % size ==0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size +1;
        }
        paginationDTO.setPagination(totalPage,page);


        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();




        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);





        return paginationDTO;
    }

    public PaginationDTO list(Integer userid, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userid);


        if(totalCount % size ==0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size +1;
        }



        if(page<1) page=1;
        if(page>totalPage) page=totalPage;


        paginationDTO.setPagination(totalPage,page);


        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userid,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();




        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);





        return paginationDTO;

    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        } else {
            //update
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
