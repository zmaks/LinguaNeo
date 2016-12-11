package com.zheltoukhov.linguaneo.repository;

import com.zheltoukhov.linguaneo.entity.WordsGroup;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Maksim on 10.12.2016.
 */
public interface GroupRepository extends CrudRepository<WordsGroup, Long> {
}
