package com.zheltoukhov.linguaneo.repository;

import com.zheltoukhov.linguaneo.entity.WordsGroup;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<WordsGroup, Long> {
}
