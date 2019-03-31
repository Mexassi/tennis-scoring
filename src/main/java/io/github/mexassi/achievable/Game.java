package io.github.mexassi.achievable;

import java.util.ArrayList;
import java.util.List;

import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.achievable.score.CurrentGameScore;

public abstract class Game extends Achievable<Point> {

    private final List<Point> playerOnePoints;
    private final List<Point> playerTwoPoints;

    Game() {
        this.playerOnePoints = new ArrayList<>();
        this.playerTwoPoints = new ArrayList<>();
    }

    @Override
    public List<Point> getPlayerOneAchievements() {
        return playerOnePoints;
    }

    @Override
    public List<Point> getPlayerTwoAchievements() {
        return playerTwoPoints;
    }

    /**
     * Get the latest points scored by both player side in the game. When no points are found a
     * {@link PointType#LOVE} is returned.
     *
     * @return an object representation of the current game score
     */
    public CurrentGameScore getCurrentGameScore() {
        List<Point> onePoints = getPlayerOneAchievements();
        List<Point> twoPoints = getPlayerTwoAchievements();

        Point love = new Point(PointType.LOVE);

        Point onePoint = (onePoints.isEmpty() ? love : onePoints.get(onePoints.size() - 1));
        Point twoPoint = (twoPoints.isEmpty() ? love : twoPoints.get(twoPoints.size() - 1));

        return new CurrentGameScore()
                .setCurrentPlayerOnePoint(onePoint)
                .setCurrentPlayerTwoPoint(twoPoint);
    }

    @Override
    public boolean isFinished() {
        CurrentGameScore currentGameScore = getCurrentGameScore();

        PointType onePoint = currentGameScore.getCurrentPlayerOnePoint().getType();
        PointType twoPoint = currentGameScore.getCurrentPlayerTwoPoint().getType();

        return onePoint.equals(PointType.WIN) || twoPoint.equals(PointType.WIN);
    }
}
