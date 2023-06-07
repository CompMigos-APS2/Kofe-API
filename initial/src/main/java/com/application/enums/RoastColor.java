package com.application.enums;

public enum RoastColor {
    VERYLIGHT(0,0,0),
    LIGHT(142, 142, 147),
    MEDIUM(0,0,0),
    DARK(0,0,0),
    VERYDARK(0,0,0);

    private int red;
    private int blue;
    private int green;

    private RoastColor(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public int getRedValue() {
        return red;
    }

    public int getBlueValue() {
        return blue;
    }

    public int getGreenValue() {
        return green;
    }

    @Override
    public String toString() {
        return red + "," + green + "," + blue;
    }
}
