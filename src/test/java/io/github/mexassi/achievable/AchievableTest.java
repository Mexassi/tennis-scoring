package io.github.mexassi.achievable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

class AchievableTest {

    @Mock
    private Achievable<Object> achievable;

    @Mock
    private Player player;

    private List<Object> playerOneAchievements;
    private List<Object> playerTwoAchievements;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        playerOneAchievements = new ArrayList<Object>(){{add("achieved");}};
        playerTwoAchievements = new ArrayList<>();

        when(achievable.getAchievedBy(player)).thenCallRealMethod();
        when(achievable.getOpponentAchievements(player)).thenCallRealMethod();

        when(achievable.getPlayerOneAchievements()).thenReturn(playerOneAchievements);
        when(achievable.getPlayerTwoAchievements()).thenReturn(playerTwoAchievements);
    }

    @Test
    @DisplayName("It should return player's one achievements")
    void getAchievedByOne() {
        when(player.getSide()).thenReturn(PlayerSide.ONE);

        List<Object> achievements = achievable.getAchievedBy(player);
        assertNotNull(achievements);
        assertEquals(playerOneAchievements, achievements);
    }

    @Test
    @DisplayName("It should return player's two achievements")
    void getAchievedByTwo() {
        when(player.getSide()).thenReturn(PlayerSide.TWO);

        List<Object> achievements = achievable.getAchievedBy(player);
        assertNotNull(achievements);
        assertEquals(playerTwoAchievements, achievements);
    }

    @Test
    @DisplayName("It should return player's two achievements")
    void getOpponentAchievements_forOne() {
        when(player.getSide()).thenReturn(PlayerSide.ONE);

        List<Object> achievements = achievable.getOpponentAchievements(player);
        assertNotNull(achievements);
        assertEquals(playerTwoAchievements, achievements);
    }

    @Test
    @DisplayName("It should return player's one achievements")
    void getOpponentAchievements_forTwo() {
        when(player.getSide()).thenReturn(PlayerSide.TWO);

        List<Object> achievements = achievable.getOpponentAchievements(player);
        assertNotNull(achievements);
        assertEquals(playerOneAchievements, achievements);
    }
}