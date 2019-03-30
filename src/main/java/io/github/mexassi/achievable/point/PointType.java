package io.github.mexassi.achievable.point;

public enum PointType {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FOURTY("40"),
    ADVANTAGE("advantage"),
    WIN("win"),
    TIE_BREAK("tie break point");

    private String label;

    PointType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
