package io.github.mexassi.rule.set;

import io.github.mexassi.achievable.TennisSet;
import io.github.mexassi.player.Player;
import io.github.mexassi.rule.Rule;

public class NextGameRule implements Rule<TennisSet> {
    @Override
    public void apply(Player player, TennisSet tennisSet) {
        if (tennisSet.isGameFinished()) {
            tennisSet.update();
        }
    }
}
