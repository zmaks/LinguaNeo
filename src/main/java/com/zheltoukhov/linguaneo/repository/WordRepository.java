package com.zheltoukhov.linguaneo.repository;

import com.zheltoukhov.linguaneo.entity.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Maksim on 07.12.2016.
 */
public interface WordRepository extends CrudRepository<Word, Long> {

    @Query(value = "SELECT t FROM Word t WHERE eng = ?1")
    Word findByEngValue(String eng);

    @Query(value = "SELECT t FROM Word t WHERE rus = ?1")
    Word findByRusValue(String rus);

    @Query(value = "SELECT t FROM Word t where rownum() <= ?1 order by lastUsage")
    List<Word> findOldest(Integer amount);

    @Query(value = "SELECT t FROM Word t where rownum() <= ?1 and mistakeIndex > 3 order by mistakeIndex")
    List<Word> findHardest(Integer amount);
}
