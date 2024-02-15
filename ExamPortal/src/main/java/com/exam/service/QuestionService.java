package com.exam.service;

import java.util.Set;

import com.exam.modelExam.Question;
import com.exam.modelExam.Quiz;

public interface QuestionService {

	
	 public Question addQuestion(Question question);

	    public Question updateQuestion(Question question);

	    public Set<Question> getQuestions();

	    public Question getQuestion(Long questionId);

	    public Set<Question> getQuestionsOfQuiz(Quiz quiz);

		public void deleteQuestion(Long quesId);
		
		public Question get(Long questionsId);
}
