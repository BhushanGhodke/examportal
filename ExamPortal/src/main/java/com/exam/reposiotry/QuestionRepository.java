package com.exam.reposiotry;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.modelExam.Question;
import com.exam.modelExam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);

}
