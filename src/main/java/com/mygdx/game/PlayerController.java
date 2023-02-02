package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Direction;
import com.mygdx.game.entities.Entity;

public class PlayerController implements InputProcessor {
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

    private void handleMovement(Direction directionForMovement) {
//        Direction directionForMovement = getFacing();

        if (directionForMovement == null) {
            return;
        }

        Utils.debug("Making " + controlled_entity.getName() + " face " + directionForMovement.name() + ".");
        controlled_entity.setFacing(directionForMovement);
        controlled_entity.queueWalk();
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

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
           case Input.Keys.LEFT -> handleMovement(Direction.WEST);
           case Input.Keys.RIGHT -> handleMovement(Direction.EAST);
           case Input.Keys.UP -> handleMovement(Direction.NORTH);
           case Input.Keys.DOWN -> handleMovement(Direction.SOUTH);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        switch (keyCode) {
            case Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN -> controlled_entity.stopWalk();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
