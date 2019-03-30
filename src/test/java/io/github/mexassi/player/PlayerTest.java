package io.github.mexassi.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Roger", PlayerSide.ONE);
    }

    @Test
    @DisplayName("It should create a player with a name and a side")
    void createPlayer() {
        assertNotNull(player);
        assertEquals("Roger", player.getName());
        assertEquals(PlayerSide.ONE, player.getSide());
    }
}