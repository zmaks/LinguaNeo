package com.zheltoukhov.linguaneo.repository;

import com.zheltoukhov.linguaneo.entity.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {

    Page<Word> findAll(Pageable pageable);

    Word findByEng(String eng);

    Word findByRus(String rus);

    List<Word> findByWordsGroupId(Long groupId);

    List<Word> findByWordsGroupId(Long groupId, Pageable pageable);

    List<Word> findByOrderByLastUsageAsc(Pageable pageable);

    @Query(value = "SELECT t FROM Word t where rownum() <= ?1 and mistakeIndex > ?2 order by mistakeIndex")
    List<Word> findHardest(Integer amount, Integer defaultMistakeInd);

    Long countByMistakeIndex(Integer mistakeIndex);

    Word findTopByOrderByMistakeIndexDesc();

}
