package io.github.mexassi.achievable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

class MatchTest {

    private Match match;

    @Mock
    private Player player;

    @Mock
    private Player opponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        match = new Match();

        when(player.getSide()).thenReturn(PlayerSide.ONE);
        when(opponent.getSide()).thenReturn(PlayerSide.TWO);
    }

    @Test
    @DisplayName("It should award points to a player")
    void pointFor() {
        match.pointFor(player);
        assertEquals("0-0, 15-0", match.getScore());

        match.pointFor(player);
        match.pointFor(player);
        match.pointFor(opponent);
        assertEquals("0-0, 40-15", match.getScore());
    }

}