package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entity;

public class AnimationController extends Task {
	ArrayList<Entity> entities;
	
	public AnimationController(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	@Override
	public void run() {
		for (Entity entity : entities)
			entity.tick();
	}

}
