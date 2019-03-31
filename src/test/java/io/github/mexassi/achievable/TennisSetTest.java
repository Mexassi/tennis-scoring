package io.github.mexassi.achievable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;
import io.github.mexassi.rule.Rule;

class TennisSetTest {

    private TennisSet tennisSet;

    @Mock
    private Game currentGame;

    @Mock
    private Rule<TennisSet> nextGameRule;

    @Mock
    private Player player;

    @Mock
    List<Game> playerOneGames;

    @Mock
    List<Game> playerTwoGames;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);

        tennisSet = new TennisSet();

        FieldSetter.setField(
                tennisSet,
                tennisSet.getClass().getDeclaredField("currentGame"),
                currentGame
        );

        FieldSetter.setField(
                tennisSet,
                tennisSet.getClass().getDeclaredField("nextGameRule"),
                nextGameRule
        );

        when(player.getSide()).thenReturn(PlayerSide.ONE);
    }

    @Test
    @DisplayName("It should invoke current game pointFor the player")
    void pointFor() {
        when(currentGame.isFinished()).thenReturn(false);

        tennisSet.pointFor(player);

        verify(currentGame).pointFor(player);
        verify(currentGame).isFinished();
        verify(nextGameRule).apply(player, tennisSet);
    }

    @Test
    @DisplayName("It should add the currentGame to the player")
    void pointFor_gameFinished() {
        when(currentGame.isFinished()).thenReturn(true);

        tennisSet.pointFor(player);

        verify(currentGame).pointFor(player);
        verify(currentGame).isFinished();
        verify(nextGameRule).apply(player, tennisSet);

        assertEquals(1, tennisSet.getAchievedBy(player).size());
    }

    @Test
    @DisplayName("It should invoke currentGame isFinished")
    void isGameFinished() {
        when(currentGame.isFinished()).thenReturn(true);

        boolean gameIsFinished = tennisSet.isGameFinished();

        assertTrue(gameIsFinished);
        verify(currentGame).isFinished();
    }

    @Test
    void isFinished() throws NoSuchFieldException {
        FieldSetter.setField(
                tennisSet,
                tennisSet.getClass().getDeclaredField("playerOneGames"),
                playerOneGames
        );
        FieldSetter.setField(
                tennisSet,
                tennisSet.getClass().getDeclaredField("playerTwoGames"),
                playerTwoGames
        );

        when(playerOneGames.size()).thenReturn(0);
        when(playerTwoGames.size()).thenReturn(0);

        assertFalse(tennisSet.isFinished());

        when(playerOneGames.size()).thenReturn(6);
        when(playerTwoGames.size()).thenReturn(5);

        assertFalse(tennisSet.isFinished());

        when(playerOneGames.size()).thenReturn(6);
        when(playerTwoGames.size()).thenReturn(7);

        assertTrue(tennisSet.isFinished());

        when(playerOneGames.size()).thenReturn(6);
        when(playerTwoGames.size()).thenReturn(4);

        assertTrue(tennisSet.isFinished());
    }
}