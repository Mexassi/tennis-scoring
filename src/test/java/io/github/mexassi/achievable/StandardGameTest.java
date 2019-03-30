package io.github.mexassi.achievable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;
import io.github.mexassi.rule.game.StandardGameRule;

class StandardGameTest {

    private StandardGame standardGame;

    @Mock
    private StandardGameRule standardGameRule;

    @Mock
    private Player player;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);

        standardGame = new StandardGame();

        when(player.getSide()).thenReturn(PlayerSide.ONE);

        FieldSetter.setField(
                standardGame,
                standardGame.getClass().getDeclaredField("standardGameRule"),
                standardGameRule
                );
    }

    @Test
    @DisplayName("It should invoke the standard game rule")
    void pointFor() {
        standardGame.pointFor(player);
        verify(standardGameRule).apply(player, standardGame);
    }

}