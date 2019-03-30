package io.github.mexassi.rule.game;

import java.util.List;

import io.github.mexassi.achievable.StandardGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class FourtyRule implements Rule<StandardGame> {

    private final PossibleDeuceRule possibleDeuceRule;

    public FourtyRule() {
        possibleDeuceRule = new PossibleDeuceRule();
    }

    @Override
    public void apply(Player player, StandardGame standardGame) {
        List<Point> playerPoints = standardGame.getAchievedBy(player);
        List<Point> opponentPoints = standardGame.getOpponentAchievements(player);

        if (opponentPoints.isEmpty()) {
            playerPoints.add(new Point(PointType.WIN));
        } else {
            possibleDeuceRule.apply(player, standardGame);
        }
    }
}
