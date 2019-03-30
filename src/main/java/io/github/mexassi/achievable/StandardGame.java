package io.github.mexassi.achievable;

import java.util.List;

import io.github.mexassi.achievable.point.Point;
import io.github.mexassi.achievable.point.PointType;
import io.github.mexassi.player.Player;

public class StandardGame extends Game {

    @Override
    public void pointFor(Player player) {
        List<Point> playerPoints = getAchievedBy(player);
        if (playerPoints.isEmpty()) {
            playerPoints.add(new Point(PointType.FIFTEEN));
        }
    }

    @Override
    public String getScore() {
        return null;
    }
}
