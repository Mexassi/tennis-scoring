package io.github.mexassi.rule.game;

import java.util.List;

import io.github.mexassi.achievable.StandardGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class PossibleDeuceRule implements Rule<StandardGame> {
    @Override
    public void apply(Player player, StandardGame standardGame) {
        List<Point> playerPoints = standardGame.getAchievedBy(player);
        List<Point> opponentPoints = standardGame.getOpponentAchievements(player);
        Point lastOpponentPoint = opponentPoints.get(opponentPoints.size() - 1);

        switch (lastOpponentPoint.getType()) {
            case ADVANTAGE:
                opponentPoints.add(new Point(PointType.FOURTY));
                break;
            case FOURTY:
                playerPoints.add(new Point(PointType.ADVANTAGE));
                break;
            default:
                playerPoints.add(new Point(PointType.WIN));
        }
    }
}
