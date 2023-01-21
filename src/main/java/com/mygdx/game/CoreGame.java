package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Ethan;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.pokemon.Pkmns;
import com.mygdx.game.entities.pokemon.Pokemon;

public class CoreGame extends ApplicationAdapter {
	
	public static float windowSize = 1.0f;
	public static int tileSize = 33;

	private static CoreGame game;

	private int width, height;
	
	SpriteBatch batch;
	Ethan ethan;
	ArrayList<Entity> entities;
	
	int frame;
	int zeile;
	int fps;
	
	long currentTime, lastTime;

	Tile[][] map;

	public static CoreGame get() {
		return CoreGame.game;
	}
	@Override
	public void create () {
		CoreGame.game = this;
		width = 800; //Gdx.graphics.getWidth();
		height = 600; //Gdx.graphics.getHeight();
		Gdx.graphics.setWindowedMode((int)(width*(windowSize /2)), (int)(height*(windowSize /2)));
		batch = new SpriteBatch();
		entities = new ArrayList<Entity>();

		map = new Tile[100][100];
		for (Tile[] tileRow : map) {
			for (int i = 0; i < tileRow.length; i++) {
				tileRow[i] = new Tile();
			}
		}
		
		ethan = new Ethan();

		Pokemon poke;
		int x = 42;
		int y = 21;

		// TODO: there are now multiple lists with the same entities.
		//  This could lead to entities being left over in one of the arrays despite being removed from the other one.
		//  It may be, that I want that to happen, but it can also lead to bugs.
		List<Entity> entityList = new ArrayList<>();

		entityList.add(ethan);
		entityList.add(Pkmns.get("Dratini"));
		entityList.add(Pkmns.get("Chillabell"));
		entityList.add(Pkmns.get("Picochilla"));
		entityList.add(Pkmns.get("Sandan"));
		entityList.add(Pkmns.get("Sandamer"));

		addToMap(0, 0, ethan);
		addToMap(0, 2, entityList.get(1));
		addToMap(1, 0, entityList.get(2));
		addToMap(1, 1, entityList.get(3));
		addToMap(2, 0, entityList.get(4));
		addToMap(2, 2, entityList.get(5));

		currentTime = System.currentTimeMillis();
		lastTime = currentTime;
		PlayerController playerController = new PlayerController(ethan);
//		Timer.schedule(new Controller(ethan), 0, 0.02f);
		Timer.schedule(new EntityController(this, entityList), 0f, 0.1f);
	}

	public void addToMap(int x, int y, GameObject object) {
		object.setLocation(x, y);
		map[y][x].objects.add(object);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		Tile tile;
		Sprite sprite;
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				tile = map[y][x];
				for (GameObject object : tile.objects) {
					if (object == null) {
						continue;
					}
					Utils.debug("Rendering " + object.getName() + " at " + object.getX() + "/" + object.getY());
					sprite = object.getCurrentSprite();
					sprite.setX((x+1) * tileSize * windowSize);
					sprite.setY((y+1) * tileSize * windowSize);
					object.draw(batch);
				}
			}
		}
		batch.end();
		fps();
	}
	
	private void fps() {
		fps++;
		currentTime = System.currentTimeMillis();
		if (currentTime-lastTime > 1000) {
			System.out.println("FPS: " + fps);
			fps = 0;
			lastTime = currentTime;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Entity e : entities)
			e.dispose();
	}
}
