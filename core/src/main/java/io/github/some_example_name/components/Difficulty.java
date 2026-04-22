package io.github.some_example_name.components;

public enum Difficulty {
    NORMAL(5, 5),
    HARD(7, 7),
    EXPERT(9, 9);

    public final int birdSpeed;
    public final int tubeSpeed;

    Difficulty(int birdSpeed, int tubeSpeed) {
        this.birdSpeed = birdSpeed;
        this.tubeSpeed = tubeSpeed;
    }
}
