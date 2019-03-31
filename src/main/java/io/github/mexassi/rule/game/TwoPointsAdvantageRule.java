package io.github.mexassi.rule.game;

import java.util.List;

import io.github.mexassi.achievable.TieBreakGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class TwoPointsAdvantageRule implements Rule<TieBreakGame> {
    @Override
    public void apply(Player player, TieBreakGame tieBreakGame) {
        List<Point> playerPoints = tieBreakGame.getAchievedBy(player);
        List<Point> opponentPoints = tieBreakGame.getOpponentAchievements(player);

        int playerPointsValue = playerPoints.size();
        boolean twoPointsAdvantage = (playerPointsValue - opponentPoints.size()) >= 2;

        if (playerPointsValue >= 7 && twoPointsAdvantage) {
            playerPoints.add(new Point(PointType.WIN));
        }
    }
}
