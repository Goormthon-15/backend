package com.jeju.goormthon.repository;

import com.jeju.goormthon.domain.TranslationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationHistoryRepository extends JpaRepository<TranslationHistory, Long> {

}