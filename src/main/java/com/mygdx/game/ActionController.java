package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.entities.Entity;

import java.util.List;

public class ActionController extends Timer.Task {

    private List<Entity> entities;

    public ActionController(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void run() {
        for (Entity e : entities) {
            switch (e.getNextAction()) {
                case MOVE -> {
                    processMovement(e);
                }
            }
        }
    }

    private void processMovement(Entity e) {
//        Utils.debug(e.getName() + " velocity: " + e.getVelocityX() + "/" + e.getVelocityY());
        Utils.debug("Making " + e.getName() + " move at " + e.getX() + "/" + e.getY());
        int x = e.getX() + e.getVelocityX();
        int y = e.getY() + e.getVelocityY();
        // TODO: collision check
        e.setLocation(x, y);
        e.executeMove();
    }
}
