package io.github.mexassi.achievable.point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.mexassi.achievable.score.CurrentGameScore;

class GameTest {

    private Game game;

    @Mock
    private Game mockedGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        game = new Game() {
            @Override
            public String getScore() {
                return null;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };

        when(mockedGame.isFinished()).thenCallRealMethod();
        when(mockedGame.getCurrentGameScore()).thenCallRealMethod();

        when(mockedGame.getPlayerOneAchievements()).thenReturn(new ArrayList<>());
        when(mockedGame.getPlayerTwoAchievements()).thenReturn(new ArrayList<>());
    }

    @Test
    @DisplayName("It should return player's one points")
    void getPlayerOneAchievements() {
        List<Point> points = game.getPlayerOneAchievements();
        assertNotNull(points);
    }

    @Test
    @DisplayName("It should return player's two points")
    void getPlayerTwoAchievements() {
        List<Point> points = game.getPlayerTwoAchievements();
        assertNotNull(points);
    }

    @Test
    @DisplayName("It should return the current game score")
    void getCurrentGameScore() {
        CurrentGameScore currentGameScore = mockedGame.getCurrentGameScore();
        assertNotNull(currentGameScore);
        assertEquals(PointType.LOVE, currentGameScore.getCurrentPlayerOnePoint().getType());
        assertEquals(PointType.LOVE, currentGameScore.getCurrentPlayerTwoPoint().getType());
    }

    @Test
    @DisplayName("It should return true when a player has won the game")
    void isFinished() {

        List<Point> playerOnePoints = new ArrayList<Point>(){{add(new Point(PointType.WIN));}};

        assertFalse(mockedGame.isFinished());

        when(mockedGame.getPlayerOneAchievements()).thenReturn(playerOnePoints);

        assertTrue(mockedGame.isFinished());
    }
}