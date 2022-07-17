package com.example.javaSurvey.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query(value = "SELECT * FROM surveys WHERE user_id = :uid", nativeQuery = true)
    List<Survey> findAllByUserId(Long uid);
}
