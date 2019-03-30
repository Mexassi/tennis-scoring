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

class StandardGameRuleTest {

    private StandardGameRule standardGameRule;

    @Mock
    private Player player;

    @Mock
    private StandardGame standardGame;

    @Mock
    private FourtyRule fourtyRule;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);

        standardGameRule = new StandardGameRule();

        when(player.getSide()).thenReturn(PlayerSide.ONE);

        FieldSetter.setField(
                standardGameRule,
                standardGameRule.getClass().getDeclaredField("fourtyRule"),
                fourtyRule
        );
    }

    @Test
    @DisplayName("It should add 15 point when the player hasn't score a point yet")
    void apply_noPoints() {
        List<Point> points = new ArrayList<>();
        when(standardGame.getAchievedBy(player)).thenReturn(points);

        standardGameRule.apply(player, standardGame);

        assertEquals(1, points.size());
        Point last = points.get(0);

        assertEquals(PointType.FIFTEEN, last.getType());
    }

    @Test
    @DisplayName("It should add 30 point when player score is fifteen")
    void apply_fifteen() {
        List<Point> points = new ArrayList<Point>(){{add(new Point(PointType.FIFTEEN));}};
        when(standardGame.getAchievedBy(player)).thenReturn(points);

        standardGameRule.apply(player, standardGame);

        assertEquals(2, points.size());
        Point last = points.get(1);

        assertEquals(PointType.THIRTY, last.getType());
    }

    @Test
    @DisplayName("It should add 40 point when player score is thirty")
    void apply_thirty() {
        List<Point> points = new ArrayList<Point>(){{add(new Point(PointType.THIRTY));}};
        when(standardGame.getAchievedBy(player)).thenReturn(points);

        standardGameRule.apply(player, standardGame);

        assertEquals(2, points.size());
        Point last = points.get(1);

        assertEquals(PointType.FOURTY, last.getType());
    }

    @Test
    @DisplayName("It should add a winning point when the player score is advantage")
    void apply_advantage() {
        List<Point> points = new ArrayList<Point>(){{add(new Point(PointType.ADVANTAGE));}};
        when(standardGame.getAchievedBy(player)).thenReturn(points);

        standardGameRule.apply(player, standardGame);

        assertEquals(2, points.size());
        Point last = points.get(1);

        assertEquals(PointType.WIN, last.getType());
    }

    @Test
    @DisplayName("It should apply the forty rule when the last point is 40")
    void apply_fourtyRule() {
        List<Point> points = new ArrayList<Point>(){{add(new Point(PointType.FOURTY));}};
        when(standardGame.getAchievedBy(player)).thenReturn(points);

        standardGameRule.apply(player, standardGame);

        verify(fourtyRule).apply(player, standardGame);
    }
}