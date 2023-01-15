package com.mygdx.game.entities;

public enum Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    static Direction valueOf(int ordinal) {
        switch (ordinal) {
            case 0: return NORTH;
            case 1: return SOUTH;
            case 2: return WEST;
            case 3: return EAST;
        }
        return null;
    }
}
