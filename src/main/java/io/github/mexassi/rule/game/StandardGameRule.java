package io.github.mexassi.rule.game;

import java.util.List;

import io.github.mexassi.achievable.StandardGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class StandardGameRule implements Rule<StandardGame> {
    @Override
    public void apply(Player player, StandardGame standardGame) {
        List<Point> playerPoints = standardGame.getAchievedBy(player);
        Point love = new Point(PointType.LOVE);
        Point lastPoint = (playerPoints.isEmpty() ? love : playerPoints.get(playerPoints.size() - 1));

        switch (lastPoint.getType()) {
            case LOVE:
                playerPoints.add(new Point(PointType.FIFTEEN));
                break;
            case FIFTEEN:
                playerPoints.add(new Point(PointType.THIRTY));
                break;
            case THIRTY:
                playerPoints.add(new Point(PointType.FOURTY));
                break;
            case FOURTY:
                break;
            case ADVANTAGE:
                playerPoints.add(new Point(PointType.WIN));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported point type " + lastPoint.getType());
        }
    }
}
