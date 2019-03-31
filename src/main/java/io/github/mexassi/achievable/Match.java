package io.github.mexassi.achievable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.mexassi.player.Player;

public class Match extends Achievable<TennisSet> {

    private final List<TennisSet> playerOneSets;
    private final List<TennisSet> playerTwoSets;
    private final List<TennisSet> playedSets;
    private TennisSet currentSet;

    public Match() {
        this.currentSet = new TennisSet();
        this.playerOneSets = new ArrayList<>();
        this.playerTwoSets = new ArrayList<>();
        this.playedSets = new ArrayList<>();
    }

    @Override
    public void pointFor(Player player) {
        currentSet.pointFor(player);

        if (currentSet.isFinished()) {
            getAchievedBy(player).add(currentSet);
            this.playedSets.add(currentSet);
            if (this.isFinished()) {
                // Stop the match at the first set won
                System.out.println(getScore());
                System.out.println(String.format("Player %s won the set", player.getName()));
                System.exit(0);
            }
        }
    }

    @Override
    public List<TennisSet> getPlayerOneAchievements() {
        return playerOneSets;
    }

    @Override
    public List<TennisSet> getPlayerTwoAchievements() {
        return playerTwoSets;
    }

    @Override
    public String getScore() {
        String currentSetScore = (!currentSet.isFinished() ? currentSet.getScore() : "");
        String playedSetsScore = playedSets.stream()
                .map(set -> String.format("%s-%s ",
                        set.getPlayerOneAchievements().size(),
                        set.getPlayerTwoAchievements().size()))
                .collect(Collectors.joining());
        return playedSetsScore + currentSetScore;
    }

    @Override
    public boolean isFinished() {
        return getPlayerOneAchievements().size() > 0 ||
                getPlayerTwoAchievements().size() > 0;
    }
}
