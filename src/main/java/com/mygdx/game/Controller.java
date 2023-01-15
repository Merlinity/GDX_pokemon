package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Direction;
import com.mygdx.game.entities.Entity;

import java.util.Objects;

public class Controller extends Task {
    Entity controlled_entity;

    GameState currentState;
    public Controller(Entity e) {
        controlled_entity = e;
        currentState = GameState.OVERWORLD;
    }

    @Override
    public void run() {
        String action;
        try {
            action = determineAction();

            switch (action) {
                case "movePlayer":
                    handleMovement();
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void handleMovement() {
        Direction facing = getFacing();

        if (facing == controlled_entity.getFacing()) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                controlled_entity.run();
            } else {
                controlled_entity.walk();
            }
        } else {
            controlled_entity.setFacing(facing);
        }
    }

    private String determineAction() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
        Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
        Gdx.input.isKeyPressed(Input.Keys.UP) ||
        Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            switch (currentState) {
                case OVERWORLD:
                    return "movePlayer";
                case MENU:
                case BATTLE:
                    return "moveCursor";
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            if (GameState.OVERWORLD.equals(currentState)) {
                return "openMenu";
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Y) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
            return "interact";
        }

        return "";
    }

    private Direction getFacing() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            return Direction.WEST;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            return Direction.EAST;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            return Direction.NORTH;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            return Direction.SOUTH;
        }
        return null;
    }
}
