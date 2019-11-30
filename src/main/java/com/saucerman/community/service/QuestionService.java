package com.saucerman.community.service;

import com.saucerman.community.dto.QuestionDTO;
import com.saucerman.community.mapper.QuestionMapper;
import com.saucerman.community.mapper.UserMapper;
import com.saucerman.community.model.Question;
import com.saucerman.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question: questions){
            User user = userMapper.findById(question.getCreator());
            //新建一个dto，将值填进去
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //加进入question
            questionDTO.setUser(user);  //加进去user

            questionDTOList.add(questionDTO);
        }
       return questionDTOList;
    }
}
