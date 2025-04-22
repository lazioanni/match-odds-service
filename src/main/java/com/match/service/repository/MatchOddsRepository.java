package com.match.service.repository;

import com.match.service.domain.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>MatchOddsRepository</h1>
 *
 * The repository for managing the {@link MatchOdds}.
 *
 * @author lioannidis
 * @version 0.1
 */
@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {
}
