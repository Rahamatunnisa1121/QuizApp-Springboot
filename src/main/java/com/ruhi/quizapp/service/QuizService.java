package com.ruhi.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ruhi.quizapp.dao.QuestionDao;
import com.ruhi.quizapp.dao.QuizDao;
import com.ruhi.quizapp.model.Question;
import com.ruhi.quizapp.model.Quiz;

@Service
public class QuizService {
	@Autowired
	QuestionDao quesDao;
	@Autowired
	QuizDao quizDao;
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions=quesDao.findRandomQuestionsByCategory(category,numQ);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
}
