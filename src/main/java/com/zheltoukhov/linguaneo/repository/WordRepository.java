package com.zheltoukhov.linguaneo.repository;

import com.zheltoukhov.linguaneo.entity.Word;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Maksim on 07.12.2016.
 */
public interface WordRepository extends CrudRepository<Word, Long> {

    Word findByEng(String eng);

    Word findByRus(String rus);

    List<Word> findByWordsGroupId(Long groupId);

    List<Word> findByOrderByLastUsageAsc(Pageable pageable);

    @Query(value = "SELECT t FROM Word t where rownum() <= ?1 and mistakeIndex > ?2 order by mistakeIndex")
    List<Word> findHardest(Integer amount, Integer defaultMistakeInd);

    Long countByMistakeIndex(Integer mistakeIndex);

    Word findTopByOrderByMistakeIndexDesc();

}
