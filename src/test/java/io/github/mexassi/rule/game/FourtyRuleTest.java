package io.github.mexassi.rule.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import io.github.mexassi.achievable.StandardGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

class FourtyRuleTest {

    private FourtyRule fourtyRule;

    private List<Point> playerPoints;

    @Mock
    private Player player;

    @Mock
    private StandardGame standardGame;

    @Mock
    private PossibleDeuceRule possibleDeuceRule;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);

        playerPoints = new ArrayList<Point>(){{add(new Point(PointType.FIFTEEN));}};

        fourtyRule = new FourtyRule();

        FieldSetter.setField(
                fourtyRule,
                fourtyRule.getClass().getDeclaredField("possibleDeuceRule"),
                possibleDeuceRule
        );

        when(player.getSide()).thenReturn(PlayerSide.ONE);

        when(standardGame.getAchievedBy(player)).thenReturn(playerPoints);
        when(standardGame.getOpponentAchievements(player))
                .thenReturn(new ArrayList<Point>(){{add(new Point(PointType.FOURTY));}});
    }

    @Test
    @DisplayName("It should add the winning point to the current player")
    void apply_opponentHasNoPoints() {
        when(standardGame.getOpponentAchievements(player)).thenReturn(new ArrayList<>());

        fourtyRule.apply(player, standardGame);

        assertEquals(2, playerPoints.size());
        Point point = playerPoints.get(1);
        assertEquals(PointType.WIN, point.getType());
    }

    @Test
    @DisplayName("It should apply the possible deuce rule")
    void apply_opponentHasPoints() {
        fourtyRule.apply(player, standardGame);

        verify(possibleDeuceRule).apply(player, standardGame);
    }

}