package io.github.mexassi.achievable;

import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;
import io.github.mexassi.rule.game.StandardGameRule;

public class StandardGame extends Game {

    private final Rule<StandardGame> standardGameRule;

    public StandardGame() {
        standardGameRule = new StandardGameRule();
    }

    @Override
    public void pointFor(Player player) {
        standardGameRule.apply(player, this);
    }

    @Override
    public String getScore() {
        return null;
    }
}
