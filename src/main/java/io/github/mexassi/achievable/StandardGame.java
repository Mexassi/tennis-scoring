package io.github.mexassi.achievable;

import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.achievable.score.CurrentGameScore;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;
import io.github.mexassi.rule.game.StandardGameRule;

public class StandardGame extends Game {

    private final Rule<StandardGame> standardGameRule;

    public StandardGame() {
        standardGameRule = new StandardGameRule();
    }

    /**
     * Assign a point for a player. Delegate the implementation to {@link StandardGameRule}
     *
     * @param player the player to assign the point for
     */
    @Override
    public void pointFor(Player player) {
        standardGameRule.apply(player, this);
    }

    @Override
    public String getScore() {
        CurrentGameScore gameScore = getCurrentGameScore();

        PointType onePoint = gameScore.getCurrentPlayerOnePoint().getType();
        PointType twoPoint = gameScore.getCurrentPlayerTwoPoint().getType();

        if (onePoint.equals(PointType.FOURTY) && twoPoint.equals(PointType.FOURTY)) {
            return "Deuce";
        }

        if (onePoint.equals(PointType.WIN) || twoPoint.equals(PointType.WIN)) {
            return "";
        }

        if (onePoint.equals(PointType.LOVE) && twoPoint.equals(PointType.LOVE)) {
            return "";
        }

        if (onePoint.equals(PointType.ADVANTAGE)) {
            return "Advantage player 1";
        }

        if (twoPoint.equals(PointType.ADVANTAGE)) {
            return "Advantage player 2";
        }

        return String.format("%s-%s", onePoint.getLabel(), twoPoint.getLabel());
    }
}
