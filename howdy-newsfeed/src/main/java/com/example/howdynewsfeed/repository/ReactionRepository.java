package com.example.howdynewsfeed.repository;

import com.example.howdynewsfeed.models.Reaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReactionRepository extends CrudRepository<Reaction,Long> {
    Optional<Reaction> findById (Long id);
}
