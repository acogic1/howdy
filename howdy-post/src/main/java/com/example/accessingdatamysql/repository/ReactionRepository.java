package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByPostIdAndReactionType_Sad(Long id);
    List<Reaction> findByPostIdAndReactionType_Like(Long id);
    List<Reaction> findByPostIdAnAndReactionType_Dislike(Long id);
    List<Reaction> findByPostIdAndReactionType_Haha (Long id);

}
