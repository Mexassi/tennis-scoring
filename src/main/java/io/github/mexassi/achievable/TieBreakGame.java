package io.github.mexassi.achievable;

import java.util.List;

import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;
import io.github.mexassi.rule.game.TwoPointsAdvantageRule;

public class TieBreakGame extends Game {

    private final Rule<TieBreakGame> twoPointsAdvantageRule;

    public TieBreakGame() {
        this.twoPointsAdvantageRule = new TwoPointsAdvantageRule();
    }

    @Override
    public void pointFor(Player player) {
        List<Point> playerPoints = getAchievedBy(player);
        playerPoints.add(new Point(PointType.TIE_BREAK));

        twoPointsAdvantageRule.apply(player, this);
    }

    @Override
    public String getScore() {
        return String.format("%s-%s",
                getPlayerOneAchievements().size(),
                getPlayerTwoAchievements().size());
    }
}
