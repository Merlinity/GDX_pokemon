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
	List<Entity> entities;
	
	int frame;
	int zeile;
	int fps;
	
	long currentTime, lastTime;

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
		entities = new ArrayList<>();

		ethan = new Ethan();

		Pokemon poke;
		int x = 42;
		int y = 21;

		List<Entity> entityList = new ArrayList<>();

		entityList.add(ethan);
		entityList.add(Pkmns.get("Dratini"));
		entityList.add(Pkmns.get("Chillabell"));
		entityList.add(Pkmns.get("Picochilla"));
		entityList.add(Pkmns.get("Sandan"));
		entityList.add(Pkmns.get("Sandamer"));

		/*0, 0,*/ ethan.setLocation(0, 0);
		/*0, 2,*/ entityList.get(1).setLocation(32, 64);
		/*1, 0,*/ entityList.get(2).setLocation(32, 15);
		/*1, 1,*/ entityList.get(3).setLocation(128, 32);
		/*2, 0,*/ entityList.get(4).setLocation(256, 256);
		/*2, 2,*/ entityList.get(5).setLocation(164, 135);

		entities = entityList;

		currentTime = System.currentTimeMillis();
		lastTime = currentTime;
		PlayerController playerController = new PlayerController(ethan);
		Timer.schedule(playerController, 0, 0.02f);
		Timer.schedule(new EntityController(this, entityList), 0f, 0.02f);
		Timer.schedule(new ActionController(entityList), 0f, 0.1f);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		Sprite sprite;
		for (Entity object : entities) {
			if (object == null) {
				continue;
			}
//			Utils.debug("Rendering " + object.getName() + " at " + object.getX() + "/" + object.getY());
			sprite = object.getCurrentSprite();
			sprite.setX(object.getX() * windowSize);
			sprite.setY(object.getY() * windowSize);
			object.draw(batch);
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
