package io.github.mexassi;

import io.github.mexassi.achievable.Match;
import io.github.mexassi.player.Player;
import io.github.mexassi.player.PlayerSide;

public class Application {

    public static void main(String... args) {
        Player playerOne = new Player("Roger", PlayerSide.ONE);
        Player playerTwo = new Player("Novak", PlayerSide.TWO);

        Match granFinal = new Match();

        granFinal.pointFor(playerOne);
        granFinal.pointFor(playerTwo);
        // this will return "0-0, 15-15"
        System.out.println(granFinal.getScore());

        granFinal.pointFor(playerOne);
        granFinal.pointFor(playerOne);
        // this will return "0-0, 40-15"
        System.out.println(granFinal.getScore());

        granFinal.pointFor(playerTwo);
        granFinal.pointFor(playerTwo);
        // this will return "0-0, Deuce"
        System.out.println(granFinal.getScore());

        granFinal.pointFor(playerOne);
        // this will return "0-0, Advantage player 1"
        System.out.println(granFinal.getScore());

        granFinal.pointFor(playerOne);
        // this will return "1-0"
        System.out.println(granFinal.getScore());
    }
}
