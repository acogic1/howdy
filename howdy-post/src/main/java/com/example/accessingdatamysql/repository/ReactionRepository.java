package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findByPostIdAndReactionType(Long post_id, Reaction.ReactionType reactionType);
}
