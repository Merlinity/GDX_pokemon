package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Direction;
import com.mygdx.game.entities.Entity;

public class PlayerController extends Task {
    private static PlayerController playerController;

    private Entity controlled_entity;

    private GameState currentState;
    public PlayerController(Entity e) {
        playerController = this;
        controlled_entity = e;
        currentState = GameState.OVERWORLD;
    }

    public static PlayerController get() {
        return PlayerController.playerController;
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
                case "moveCursor":
                    Utils.debug("There are no menus to move cursor in.");
                    break;
                case "openMenu":
                    Utils.debug("Menu not yet implemented.");
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void handleMovement() {
        Direction directionForMovement = getFacing();

        if (directionForMovement == null) {
            return;
        }

        if (directionForMovement == controlled_entity.getFacing()) {
            Utils.debug("Making " + controlled_entity.getName() + " move.");
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                controlled_entity.run();
            } else {
                controlled_entity.walk();
            }
        } else {
            Utils.debug("Making " + controlled_entity.getName() + " face " + directionForMovement.name() + ".");
            controlled_entity.setFacing(directionForMovement);
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
