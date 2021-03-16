package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Komentar;
import org.springframework.data.repository.CrudRepository;

public interface KomentarRepository extends CrudRepository<Komentar, Integer> {
}
