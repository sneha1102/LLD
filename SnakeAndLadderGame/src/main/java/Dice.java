package main.java;

public class Dice {
    private final int maxValue;
    private final int minValue;

    public Dice(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int roll() {
        // generate random no between min and max, + minValuee means start from minValue instead of zero
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

}
