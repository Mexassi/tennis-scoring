package io.github.mexassi.rule.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.mexassi.achievable.TieBreakGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

class TwoPointsAdvantageRuleTest {

    private TwoPointsAdvantageRule twoPointsAdvantageRule;

    @Mock
    private Player player;

    @Mock
    private TieBreakGame tieBreakGame;

    @Mock
    private List<Point> playerPoints;

    @Mock
    private List<Point> opponentPoints;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        twoPointsAdvantageRule = new TwoPointsAdvantageRule();

        when(player.getSide()).thenReturn(PlayerSide.ONE);
        when(tieBreakGame.getAchievedBy(player)).thenReturn(playerPoints);
        when(tieBreakGame.getOpponentAchievements(player)).thenReturn(opponentPoints);
    }

    @Test
    @DisplayName("It should add a winning point when score is 7 or greater and there is a 2 points advantage")
    void apply() {
        ArgumentCaptor<Point> captor = ArgumentCaptor.forClass(Point.class);

        when(playerPoints.size()).thenReturn(7);
        when(opponentPoints.size()).thenReturn(5);

        twoPointsAdvantageRule.apply(player, tieBreakGame);

        verify(playerPoints).add(captor.capture());

        Point awarded = captor.getValue();

        assertNotNull(awarded);
        assertEquals(PointType.WIN, awarded.getType());
    }

}