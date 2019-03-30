package io.github.mexassi.achievable;

import java.util.ArrayList;
import java.util.List;

import io.github.mexassi.observable.Observable;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;
import io.github.mexassi.rule.set.NextGameRule;

public class TennisSet extends Achievable<Game> implements Observable {

    private final List<Game> playerOneGames;
    private final List<Game> playerTwoGames;
    private Game currentGame;
    private Rule<TennisSet> nextGameRule;

    public TennisSet() {
        playerOneGames = new ArrayList<>();
        playerTwoGames = new ArrayList<>();
        currentGame = new StandardGame();
        nextGameRule = new NextGameRule();
    }


    @Override
    public void pointFor(Player player) {
        currentGame.pointFor(player);

        if (currentGame.isFinished()) {
            getAchievedBy(player).add(currentGame);
        }

        nextGameRule.apply(player, this);
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
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void update() {

    }

    public boolean isGameFinished() {
        return this.currentGame.isFinished();
    }
}
