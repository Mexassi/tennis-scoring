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

class StandardGameTest {

    private StandardGame standardGame;

    @Mock
    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        standardGame = new StandardGame();

        when(player.getSide()).thenReturn(PlayerSide.ONE);
    }

    @Test
    @DisplayName("It should add a point for the player")
    void pointFor() {
        standardGame.pointFor(player);
        assertEquals(1, standardGame.getAchievedBy(player).size());
    }

}