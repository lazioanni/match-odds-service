package com.match.service.service;

import com.match.service.dto.MatchOddsDTO;
import com.match.service.exception.MatchNotFoundException;
import com.match.service.exception.MatchOddNotFoundException;
import com.match.service.repository.MatchOddsRepository;
import com.match.service.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.match.service.data.TestMatchData.createFirstMatch;
import static com.match.service.data.TestMatchOddsDTOData.createFirstMatchOddsDTO;
import static com.match.service.data.TestMatchOddsDTOData.createSecondMatchOddsDTO;
import static com.match.service.data.TestMatchOddsData.createFirstMatchOdds;
import static com.match.service.data.TestMatchOddsData.createSecondMatchOdds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchOddsServiceImplTest {

    @Mock private MatchRepository matchRepository;

    @Mock private MatchOddsRepository matchOddsRepository;

    @InjectMocks MatchOddsServiceImpl matchOddsService;


    @Test
    void test_getAllOdds_thenSuccess() {
        final var firstMatchOdds = createFirstMatchOdds();
        final var secondMatchOdds = createSecondMatchOdds();
        when(matchOddsRepository.findAll()).thenReturn(List.of(firstMatchOdds,secondMatchOdds));

        List<MatchOddsDTO> matchOdds = matchOddsService.getAllOdds();

        assertThat(matchOdds).hasSize(2);
        assertThat(matchOdds.get(0).matchId()).isEqualTo(1L);
        assertThat(matchOdds.get(1).matchId()).isEqualTo(2L);
    }

    @Test
    void testGetOddById_thenSuccess() {
        final var oddId = 1L;
        final var firstMatchOdds = createFirstMatchOdds();
        when(matchOddsRepository.findById(any())).thenReturn(Optional.of(firstMatchOdds));

        final var match = matchOddsService.getOddsById(oddId);

        assertThat(match).isPresent().get().extracting(MatchOddsDTO::id).isEqualTo(oddId);
    }

    @Test
    void testGetOddById_whenIdDoesNotExist_thenThrowMatchOddNotFoundException() {
        final var oddId = 1L;

        assertThatThrownBy(() -> matchOddsService.getOddsById(oddId))
                .isInstanceOf(MatchOddNotFoundException.class)
                .hasMessage("Match odd with ID 1 not found");
    }

    @Test
    void testCreateOdds_thenSuccess() {
        final var firstMatch = createFirstMatch();
        final var firstMatchOdds = createFirstMatchOdds();
        final var firstMatchOddsDTO = createFirstMatchOddsDTO();

        when(matchRepository.findById(any())).thenReturn(Optional.of(firstMatch));
        when(matchOddsRepository.save(any())).thenReturn(firstMatchOdds);

        matchOddsService.createOdds(firstMatchOddsDTO);

        verify(matchOddsRepository,times(1)).save(any());
    }

    @Test
    void testCreateOdds_whenMatchIdDoesNotExist_thenThrowMatchNotFoundException() {
        final var firstMatchOddsDTO = createFirstMatchOddsDTO();

        assertThatThrownBy(() -> matchOddsService.createOdds(firstMatchOddsDTO))
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessage("Match with ID 1 not found");
    }


    @Test
    void testUpdateOdds_thenSuccess() {
        final var oddId = 1L;
        final var firstMatchOdds = createFirstMatchOdds();
        final var secondMatchOdds = createSecondMatchOdds();
        final var secondMatchOddsDTO = createSecondMatchOddsDTO();

        when(matchOddsRepository.findById(any())).thenReturn(Optional.of(firstMatchOdds));
        when(matchOddsRepository.save(any())).thenReturn(secondMatchOdds);

        final var updateOdds = matchOddsService.updateOdds(oddId,secondMatchOddsDTO);

        assertThat(updateOdds)
                .isNotNull()
                .extracting(MatchOddsDTO::id)
                .isEqualTo(secondMatchOddsDTO.id());

        verify(matchOddsRepository,times(1)).save(any());
    }

    @Test
    void testUpdateOdds_whenIdDoesNotExist_thenThrowMatchOddNotFoundException() {
        final var oddId = 1L;
        final var secondMatchOddsDTO = createSecondMatchOddsDTO();

        assertThatThrownBy(() -> matchOddsService.updateOdds(oddId,secondMatchOddsDTO))
                .isInstanceOf(MatchOddNotFoundException.class)
                .hasMessage("MatchOdds with ID 1 not found");
    }

    @Test
    void testDeleteMatch_thenSuccess() {
        final var oddId = 1L;

        when(matchOddsRepository.existsById(any())).thenReturn(true);

        matchOddsService.deleteOdds(oddId);

        verify(matchOddsRepository,times(1)).deleteById(any());
    }

    @Test
    void testDeleteMatchOdd_whenIdDoesNotExist_thenThrowMatchOddNotFoundException() {
        final var oddId = 1L;

        assertThatThrownBy(() -> matchOddsService.deleteOdds(oddId))
                .isInstanceOf(MatchOddNotFoundException.class)
                .hasMessage("MatchOdds with ID 1 not found");
    }
}