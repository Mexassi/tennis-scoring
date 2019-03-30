package io.github.mexassi.achievable;

import java.util.List;

import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

public abstract class Achievable<T> {

    public abstract void pointFor(Player player);

    public abstract List<T> getPlayerOneAchievements();

    public abstract List<T> getPlayerTwoAchievements();

    public abstract String getScore();

    public abstract boolean isFinished();

    @SuppressWarnings("Duplicates")
    public List<T> getAchievedBy(Player player) {
        final PlayerSide side = player.getSide();

        switch (side) {
            case ONE:
                return getPlayerOneAchievements();
            case TWO:
                return getPlayerTwoAchievements();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported player side %s", side));
        }
    }

    @SuppressWarnings("Duplicates")
    public List<T> getOpponentAchievements(Player player) {
        final PlayerSide side = player.getSide();

        switch (side) {
            case TWO:
                return getPlayerOneAchievements();
            case ONE:
                return getPlayerTwoAchievements();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported player side %s", side));
        }
    }
}
