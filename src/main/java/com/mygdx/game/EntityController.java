package com.mygdx.game;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.GameObject;

public class EntityController extends Task {

	private static EntityController entityController;

	Tile[][] map;

	public EntityController(CoreGame game, Tile[][] map) {
		EntityController.entityController = this;
		this.map = map;
	}

	public static EntityController get() {
		return EntityController.entityController;
	}

	public void translateEntity(int x, int y, Entity entity) {
		if (!isCorrectObjectPosition(entity)) {
			return;
		}

		Tile targetTile = map[entity.getY()][entity.getX()];
	}

	private boolean isCorrectObjectPosition(GameObject object) {
		if (object == null) {
			return false;
		}
		return map[object.getY()][object.getX()].objects.contains(object);
	}

	public void teleportEntity(int x, int y, Entity entity) {

	}
	
	@Override
	public void run() {
		Tile tile;
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				tile = map[y][x];
				for (GameObject object : tile.objects) {
					if (object instanceof Entity) {
						((Entity) object).act();
					}
				}
			}
		}
	}

}
