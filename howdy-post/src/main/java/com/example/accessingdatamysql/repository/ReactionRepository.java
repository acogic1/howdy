package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
