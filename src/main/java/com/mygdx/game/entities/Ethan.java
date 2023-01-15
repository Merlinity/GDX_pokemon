package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Utils;

public class Ethan extends Entity {
	
	protected String name = "ethan";
	
	public Ethan() {
	}
	
	protected void init() {
		spritesheet = new Texture(Utils.BASE_ASSETS_PATH + "ethan" + ".png");
		
		animation = TextureRegion.split(spritesheet, 19, 28);
		currentSprite = new Sprite(animation[0][frame]);
	}
	
	@Override
	public void dispose() {
		spritesheet.dispose();
	}
}
