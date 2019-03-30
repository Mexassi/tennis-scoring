package io.github.mexassi.rule;

import io.github.mexassi.achievable.Achievable;
import io.github.mexassi.player.Player;

public interface Rule<T extends Achievable> {

    void apply(Player player, T t);

}
