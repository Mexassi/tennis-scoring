package io.github.mexassi.achievable;

import java.util.List;

import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

public abstract class Achievable<T> {

    /**
     * Handles the logic for assigning a point to a player
     * @param player the player to assign the point for
     */
    public abstract void pointFor(Player player);

    /**
     * @return a list of player one achievements
     */
    public abstract List<T> getPlayerOneAchievements();

    /**
     * @return a list of player two achievements
     */
    public abstract List<T> getPlayerTwoAchievements();

    /**
     * Get a string representation of the current score
     */
    public abstract String getScore();

    /**
     * Determine if the achievable is in a finished state
     */
    public abstract boolean isFinished();

    /**
     * Find the achievements for a given player
     *
     * @param player the player to find the achievements for
     * @return a list of player achievements
     */
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

    /**
     * Find the opponent achievements for a given player
     *
     * @param player the player to find the opponent achievements for
     * @return a list of opponent achievement
     */
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
