package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entity;

public class Controller extends Task {
	Entity controlled_entity;
	public Controller(Entity e) {
		controlled_entity = e;
	}

	@Override
	public void run() {
		try {
//			controlled_entity.stopWalk();
			
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if (controlled_entity.getFacing() == Entity.WEST ) 	controlled_entity.walk();
				else if (!controlled_entity.isWalking())			controlled_entity.setFacing(Entity.WEST);
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if (controlled_entity.getFacing() == Entity.EAST) 	controlled_entity.walk();
				else if (!controlled_entity.isWalking())			controlled_entity.setFacing(Entity.EAST);
			} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				if (controlled_entity.getFacing() == Entity.NORTH) 	controlled_entity.walk();
				else if (!controlled_entity.isWalking())			controlled_entity.setFacing(Entity.NORTH);
			} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				if (controlled_entity.getFacing() == Entity.SOUTH) 	controlled_entity.walk();
				else if (!controlled_entity.isWalking())			controlled_entity.setFacing(Entity.SOUTH);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
