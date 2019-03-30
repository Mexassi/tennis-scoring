package io.github.mexassi.rule.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.mexassi.achievable.StandardGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

class PossibleDeuceRuleTest {

    private PossibleDeuceRule possibleDeuceRule;

    @Mock
    private Player player;

    @Mock
    private StandardGame standardGame;

    private List<Point> playerPoints;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        possibleDeuceRule = new PossibleDeuceRule();
        playerPoints = new ArrayList<>();

        when(player.getSide()).thenReturn(PlayerSide.ONE);
        when(standardGame.getAchievedBy(player)).thenReturn(playerPoints);
    }

    @Test
    @DisplayName("It should add advantage point to the current player when the opponent point is 40")
    void apply_advantage() {
        List<Point> opponentPoints = new ArrayList<Point>(){{add(new Point(PointType.FOURTY));}};
        when(standardGame.getOpponentAchievements(player)).thenReturn(opponentPoints);

        possibleDeuceRule.apply(player, standardGame);

        assertEquals(1, playerPoints.size());
        Point point = playerPoints.get(0);
        assertEquals(PointType.ADVANTAGE, point.getType());
    }

    @Test
    @DisplayName("It should add 40 point to the opponent when the opponent last point was advantage")
    void apply_opponentFourty() {
        List<Point> opponentPoints = new ArrayList<Point>(){{add(new Point(PointType.ADVANTAGE));}};
        when(standardGame.getOpponentAchievements(player)).thenReturn(opponentPoints);

        possibleDeuceRule.apply(player, standardGame);

        assertEquals(2, opponentPoints.size());
        Point point = opponentPoints.get(1);
        assertEquals(PointType.FOURTY, point.getType());
    }

    @Test
    @DisplayName("It should add winning point to the current player when the opponent has any other point")
    void apply_winningPoint() {
        List<Point> opponentPoints = new ArrayList<Point>(){{add(new Point(PointType.THIRTY));}};
        when(standardGame.getOpponentAchievements(player)).thenReturn(opponentPoints);

        possibleDeuceRule.apply(player, standardGame);

        assertEquals(1, playerPoints.size());
        Point point = playerPoints.get(0);
        assertEquals(PointType.WIN, point.getType());
    }
}