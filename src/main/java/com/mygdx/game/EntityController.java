package com.mygdx.game;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.GameObject;

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

	public void translateEntity(int x, int y, Entity entity) {
		if (entity == null || (x == 0 && y == 0)) {
			return;
		}

		teleportEntity(entity.getX() + x, entity.getY() + y, entity);
	}

	public void teleportEntity(int x, int y, Entity entity) {
		Tile sourceTile = getTile(entity);
		if (sourceTile == null || !sourceTile.objects.contains(entity)) {
			return;
		}

		Utils.debug(String.format("Trying to move %s to %d/%d", entity.getName(), x, y));

		Tile targetTile = null;
		try {
			targetTile = CoreGame.get().map[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return;
		}

		for (GameObject object : targetTile.objects) {
			if (object.isSolid()) {
				return;
			}
		}

		CoreGame.get().addToMap(x, y, entity);
		sourceTile.objects.remove(entity);
	}

	private Tile getTile(GameObject object) {
		Tile tile = null;
		try {
			tile = CoreGame.get().map[object.getY()][object.getX()];
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			e.printStackTrace();
		}
		return tile;
	}
	
	@Override
	public void run() {
		PlayerController.get().run();

		List<Entity> copyOfEntities = new ArrayList<>(entities);
		for (Entity entity : copyOfEntities) {
			Utils.debug("Making " + entity.getName() + " act at " + entity.getX() + "/" + entity.getY());

			try {
				entity.act();
			} catch (ConcurrentModificationException e) {
				e.printStackTrace();
			}
		}
	}

}
