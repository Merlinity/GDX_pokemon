package com.mygdx.game;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.GameObject;

import java.util.ConcurrentModificationException;

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

		Tile targetTile = null;
		try {
			targetTile = map[y][x];
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
			tile = map[object.getY()][object.getX()];
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			e.printStackTrace();
		}
		return tile;
	}
	
	@Override
	public void run() {
		Tile tile;
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				tile = map[y][x];
				// TODO: ConcurrentModificationException when trying to move.
				//  Which makes sense, considering the fact that I am actually taking the moving object out of the array
				for (GameObject object : tile.objects) {
					Utils.debug("Making " + object.getName() + " act at " + object.getX() + "/" + object.getY());

					if (object instanceof Entity) {
						try {
							((Entity) object).act();
						} catch (ConcurrentModificationException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
