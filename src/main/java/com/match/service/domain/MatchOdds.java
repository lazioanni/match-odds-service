package com.match.service.domain;

import com.match.service.enums.Specifier;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * <h1>MatchOdds</h1>
 * <p>
 * The MatchOdds entity.
 *
 * @author lioannidis
 * @version 0.1
 */
@Data
@Entity
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "match_odds")
public class MatchOdds {

    @Id
    @Column(name = "match_odd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Enumerated(EnumType.STRING)
    @Column(name = "specifier")
    private Specifier specifier;

    @Column(name = "odd")
    private BigDecimal odd;
}
