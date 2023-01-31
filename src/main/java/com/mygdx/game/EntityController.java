package com.mygdx.game;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class EntityController extends Task {

	private static EntityController entityController;

	List<Entity> entities;

	public EntityController(CoreGame game, List<Entity> entities) {
		EntityController.entityController = this;
		this.entities = entities;
	}

	public static EntityController get() {
		return EntityController.entityController;
	}
	
	@Override
	public void run() {

		List<Entity> copyOfEntities = new ArrayList<>(entities);
		for (Entity entity : copyOfEntities) {
//			Utils.debug("Making " + entity.getName() + " act at " + entity.getX() + "/" + entity.getY());
			entity.prepareNextAction();
		}
	}

}
