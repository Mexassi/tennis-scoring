package io.github.mexassi.rule.set;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.mexassi.achievable.TennisSet;
import io.github.mexassi.player.Player;

class NextGameRuleTest {

    private NextGameRule nextGameRule;

    @Mock
    private Player player;

    @Mock
    private TennisSet tennisSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        nextGameRule = new NextGameRule();
    }

    @Test
    @DisplayName("it should update the tennis set if the game is finished")
    void apply() {
        when(tennisSet.isGameFinished()).thenReturn(false);
        nextGameRule.apply(player, tennisSet);
        verify(tennisSet, never()).update();

        when(tennisSet.isGameFinished()).thenReturn(true);
        nextGameRule.apply(player, tennisSet);
        verify(tennisSet).update();
    }

}