package com.match.service.repository;

import com.match.service.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>MatchRepository</h1>
 *
 * The repository for managing the {@link Match}.
 *
 * @author lioannidis
 * @version 0.1
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
