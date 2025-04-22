package com.match.service.service;

import com.match.service.dto.MatchDTO;
import com.match.service.exception.MatchNotFoundException;
import com.match.service.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.match.service.data.TestMatchDTOData.createFirstMatchDTO;
import static com.match.service.data.TestMatchDTOData.createSecondMatchDTO;
import static com.match.service.data.TestMatchData.createFirstMatch;
import static com.match.service.data.TestMatchData.createSecondMatch;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @Mock private MatchRepository matchRepository;
    @InjectMocks MatchServiceImpl matchService;


    @Test
    void testGetAllMatches_thenSuccess() {
        final var firstMatch = createFirstMatch();
        final var secondMatch = createSecondMatch();
        when(matchRepository.findAll()).thenReturn(List.of(firstMatch,secondMatch));

        List<MatchDTO> matches = matchService.getAllMatches();

        assertThat(matches).hasSize(2);
        assertThat(matches.get(0).description()).isEqualTo("Big Athens Derby");
        assertThat(matches.get(1).description()).isEqualTo("Big Thessaloniki's Derby");
    }

    @Test
    void testGetMatchById_thenSuccess() {
        final var matchId = 1L;
        final var firstMatch = createFirstMatch();
        when(matchRepository.findById(any())).thenReturn(Optional.of(firstMatch));

        final var match = matchService.getMatchById(matchId);

        assertThat(match).isPresent().get().extracting(MatchDTO::id).isEqualTo(matchId);
    }

    @Test
    void testGetMatchById_whenIdDoesNotExist_thenThrowMatchNotFoundException() {
        final var matchId = 1L;

        assertThatThrownBy(() -> matchService.getMatchById(matchId))
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessage("Match with ID 1 not found");
    }

    @Test
    void testCreateMatch_thenSuccess() {
        final var firstMatchDTO = createFirstMatchDTO();
        final var firstMatch = createFirstMatch();

        when(matchRepository.save(any())).thenReturn(firstMatch);

        matchService.createMatch(firstMatchDTO);

        verify(matchRepository,times(1)).save(any());
    }

    @Test
    void testUpdateMatch_thenSuccess() {
        final var matchId = 1L;
        final var secondMatchDTO = createSecondMatchDTO();
        final var firstMatch = createFirstMatch();
        final var secondMatch = createSecondMatch();

        when(matchRepository.findById(any())).thenReturn(Optional.of(firstMatch));
        when(matchRepository.save(any())).thenReturn(secondMatch);

        final var updatedMatch = matchService.updateMatch(matchId,secondMatchDTO);

        assertThat(updatedMatch)
                .isPresent()
                .get()
                .extracting(MatchDTO::description)
                .isEqualTo(secondMatchDTO.description());

        verify(matchRepository,times(1)).save(any());
    }

    @Test
    void testUpdateMatch_whenIdDoesNotExist_thenThrowMatchNotFoundException() {
        final var matchId = 1L;
        final var secondMatchDTO = createSecondMatchDTO();

        assertThatThrownBy(() -> matchService.updateMatch(matchId,secondMatchDTO))
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessage("Match with ID 1 not found");
    }

    @Test
    void testDeleteMatch_thenSuccess() {
        final var matchId = 1L;

        when(matchRepository.existsById(any())).thenReturn(true);

        matchService.deleteMatch(matchId);

        verify(matchRepository,times(1)).deleteById(any());
    }

    @Test
    void testDeleteMatch_whenIdDoesNotExist_thenThrowMatchNotFoundException() {
        final var matchId = 1L;

        assertThatThrownBy(() -> matchService.deleteMatch(matchId))
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessage("Match with ID 1 not found");
    }
}