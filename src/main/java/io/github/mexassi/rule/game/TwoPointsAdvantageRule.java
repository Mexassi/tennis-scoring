package io.github.mexassi.rule.game;

import java.util.List;

import io.github.mexassi.achievable.TieBreakGame;
import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class TwoPointsAdvantageRule implements Rule<TieBreakGame> {

    /**
     * Assign a winning point when the player has a score that is equal or greater than 7 and has
     * at least 2 or more points advantage over the opponent.
     *
     * @param player that scored the point
     * @param tieBreakGame the game the player scored the point in
     */
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
