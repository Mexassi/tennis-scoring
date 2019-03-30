package io.github.mexassi.achievable.score;

import java.util.Objects;

import io.github.mexassi.achievable.point.Point;

public class CurrentGameScore {

    private Point currentPlayerOnePoint;
    private Point currentPlayerTwoPoint;

    public Point getCurrentPlayerOnePoint() {
        return currentPlayerOnePoint;
    }

    public CurrentGameScore setCurrentPlayerOnePoint(Point currentPlayerOnePoint) {
        this.currentPlayerOnePoint = currentPlayerOnePoint;
        return this;
    }

    public Point getCurrentPlayerTwoPoint() {
        return currentPlayerTwoPoint;
    }

    public CurrentGameScore setCurrentPlayerTwoPoint(Point currentPlayerTwoPoint) {
        this.currentPlayerTwoPoint = currentPlayerTwoPoint;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentGameScore currentGameScore = (CurrentGameScore) o;
        return Objects.equals(currentPlayerOnePoint, currentGameScore.currentPlayerOnePoint) &&
                Objects.equals(currentPlayerTwoPoint, currentGameScore.currentPlayerTwoPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPlayerOnePoint, currentPlayerTwoPoint);
    }

    @Override
    public String toString() {
        return "CurrentGameScore{" +
                "currentPlayerOnePoint=" + currentPlayerOnePoint +
                ", currentPlayerTwoPoint=" + currentPlayerTwoPoint +
                '}';
    }
}
