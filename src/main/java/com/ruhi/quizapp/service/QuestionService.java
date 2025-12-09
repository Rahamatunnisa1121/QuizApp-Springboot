package com.ruhi.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruhi.quizapp.dao.QuestionDao;
import com.ruhi.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}
	
	public List<Question> getQuestionsByCategory(String category){
		return questionDao.findByCategory(category);
	}

	public String addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.save(question);
		return "success!";
	}
}
