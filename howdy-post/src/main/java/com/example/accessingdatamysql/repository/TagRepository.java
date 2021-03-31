package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
