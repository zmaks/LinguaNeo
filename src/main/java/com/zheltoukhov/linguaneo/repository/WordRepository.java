package com.zheltoukhov.linguaneo.repository;

import com.zheltoukhov.linguaneo.entity.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Maksim on 07.12.2016.
 */
public interface WordRepository extends CrudRepository<Word, Long> {

    @Query(value = "SELECT t FROM Word t WHERE engValue = ?1")
    List<Word> findByEngValue(String engValue);

    @Query(value = "SELECT t FROM Word t WHERE rusValue = ?1")
    List<Word> findByRusValue(String rusValue);
}
