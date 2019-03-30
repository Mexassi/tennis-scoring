package io.github.mexassi.player;

import java.util.Objects;

public class Player {

    private final String name;
    private final PlayerSide side;

    public Player(String name, PlayerSide side) {
        this.name = name;
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public PlayerSide getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                side == player.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, side);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", side=" + side +
                '}';
    }
}
