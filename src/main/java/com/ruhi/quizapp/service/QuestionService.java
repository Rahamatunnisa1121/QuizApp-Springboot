package com.ruhi.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ruhi.quizapp.dao.QuestionDao;
import com.ruhi.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {   
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace(); //prints complete error details to console
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace(); //prints complete error details to console
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			questionDao.save(question);
			return new ResponseEntity<>("success!",HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong on the server.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
