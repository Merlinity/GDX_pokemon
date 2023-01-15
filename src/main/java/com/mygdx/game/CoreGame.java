package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Ethan;
import com.mygdx.game.entities.pokemon.Pkmns;
import com.mygdx.game.entities.pokemon.Pokemon;

public class CoreGame extends ApplicationAdapter {
	
	public static float size = 4.0f;
	private int width, height;
	
	SpriteBatch batch;
	Pokemon nagelotz;
	TextureRegion[][] nagelotzAnimation;
	Sprite nagelotzSprite;
	Ethan ethan;
	TextureRegion[][] ethanAnimation;
	ArrayList<Entity> entities;
	
	int frame;
	int zeile;
	int fps;
	
	long currentTime, lastTime;
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		Gdx.graphics.setWindowedMode((int)(width*(size/2)), (int)(height*(size/2)));
		batch = new SpriteBatch();
		entities = new ArrayList<Entity>();
		
		ethan = new Ethan();
		ethan.getCurrentSprite().setPosition(21*size, 21*size);
		
		Ethan ethan2 = new Ethan();
		ethan2.getCurrentSprite().setPosition(84*size, 105*size);
		
		Pokemon poke = null;
		int x = 42;
		int y = 21;
		
		poke = Pkmns.get("Sandan");
		poke.getCurrentSprite().setPosition(x*size, y*size);
		
		entities.add(poke);
		entities.add(ethan);
		entities.add(ethan2);
		
		currentTime = System.currentTimeMillis();
		lastTime = currentTime;
		Timer.schedule(new Controller(ethan), 0f, 0.1f);
		Timer.schedule(new AnimationController(entities), 0f, 0.099f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		batch.begin();
		for (Entity entity : entities)
			entity.draw(batch);
		batch.end();
		fps();
	}
	
	private void fps() {
		fps++;
		currentTime = System.currentTimeMillis();
		if (currentTime-lastTime>1000) {
			System.out.println("FPS: "+fps);
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
