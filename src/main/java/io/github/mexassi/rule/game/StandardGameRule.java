package io.github.mexassi.rule.game;

import java.util.List;

import io.github.mexassi.achievable.StandardGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class StandardGameRule implements Rule<StandardGame> {

    private final FourtyRule fourtyRule;

    public StandardGameRule() {
        fourtyRule = new FourtyRule();
    }

    /**
     * Assign a point to a player. The implementation changes when 40 point where scored and the
     * {@link FourtyRule} is applied.
     *
     * @param player that scored the point
     * @param standardGame the game the player scored the point in
     */
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
                fourtyRule.apply(player, standardGame);
                break;
            case ADVANTAGE:
                playerPoints.add(new Point(PointType.WIN));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported point type " + lastPoint.getType());
        }
    }
}
