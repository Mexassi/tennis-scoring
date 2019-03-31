package io.github.mexassi.achievable;

import java.util.ArrayList;
import java.util.List;

import io.github.mexassi.player.Player;

public class TennisSet extends Achievable<Game> {

    private final List<Game> playerOneGames;
    private final List<Game> playerTwoGames;
    private Game currentGame;

    public TennisSet() {
        playerOneGames = new ArrayList<>();
        playerTwoGames = new ArrayList<>();
        currentGame = new StandardGame();
    }


    @Override
    public void pointFor(Player player) {
        currentGame.pointFor(player);

        if (currentGame.isFinished()) {
            getAchievedBy(player).add(currentGame);
        }

        if (this.isGameFinished()) {
            updateGame();
        }
    }

    @Override
    public List<Game> getPlayerOneAchievements() {
        return playerOneGames;
    }

    @Override
    public List<Game> getPlayerTwoAchievements() {
        return playerTwoGames;
    }

    @Override
    public String getScore() {
        String currentGameScore = (currentGame.getScore() != null ? ", " + currentGame.getScore() : "");
        return String.format("%s-%s%s",
                playerOneGames.size(),
                playerTwoGames.size(),
                currentGameScore);    }

    @Override
    public boolean isFinished() {
        int oneGames = playerOneGames.size();
        int twoGames = playerTwoGames.size();

        boolean oneWon = (oneGames == 6 && (oneGames - twoGames) >= 2);
        boolean twoWon = (twoGames == 6 && (twoGames - oneGames) >= 2);
        boolean wonAtTieBreak = (oneGames + twoGames) == 13;

        return (oneWon || twoWon || wonAtTieBreak);
    }

    private void updateGame() {
        List<Game> playerOneGames = getPlayerOneAchievements();
        List<Game> playerTwoGames = getPlayerTwoAchievements();

        int wonByOne = playerOneGames.size();
        int wonByTwo = playerTwoGames.size();

        currentGame = getNextGame(wonByOne, wonByTwo);
    }

    public boolean isGameFinished() {
        return this.currentGame.isFinished();
    }

    private Game getNextGame(int wonByOne, int wonByTwo) {
        if (isItATieBreak(wonByOne, wonByTwo)) {
            return new TieBreakGame();
        }

        if (isExtraGameRequired(wonByOne, wonByTwo)) {
            return new StandardGame();
        }

        if (hasAnyoneWon(wonByOne, wonByTwo)) {
            return null;
        }

        return new StandardGame();
    }

    private boolean hasAnyoneWon(int wonByOne, int wonByTwo) {
        boolean playerOneWon = (wonByOne >= 6 && (wonByOne - wonByTwo) >= 2);
        boolean playerTwoWon = (wonByTwo >= 6 && (wonByTwo - wonByOne) >= 2);
        return playerOneWon || playerTwoWon;
    }

    private boolean isExtraGameRequired(int wonByOne, int wonByTwo) {
        return isSumEqual(wonByOne, wonByTwo, 11);
    }

    private boolean isItATieBreak(int wonByOne, int wonByTwo) {
        return isSumEqual(wonByOne, wonByTwo, 12);
    }

    private boolean isSumEqual(int argOne, int argTwo, int expected) {
        return (argOne + argTwo) == expected;
    }
}
