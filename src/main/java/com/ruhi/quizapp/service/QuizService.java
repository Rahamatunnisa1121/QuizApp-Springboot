package com.ruhi.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ruhi.quizapp.dao.QuestionDao;
import com.ruhi.quizapp.dao.QuizDao;
import com.ruhi.quizapp.model.Question;
import com.ruhi.quizapp.model.QuestionWrapper;
import com.ruhi.quizapp.model.Quiz;
import com.ruhi.quizapp.model.Response;

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
	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questionsFromDB=quiz.get().getQuestions();
		//convert each question of a quiz to questionWrapper
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Question q:questionsFromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int rightAns=0,i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				rightAns++;
			}
			i++;
		}
		return new ResponseEntity<>(rightAns,HttpStatus.OK);
	}
}
