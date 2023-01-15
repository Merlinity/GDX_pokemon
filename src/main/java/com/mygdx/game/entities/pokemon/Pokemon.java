package com.mygdx.game.entities.pokemon;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Utils;
import com.mygdx.game.entities.Direction;
import com.mygdx.game.entities.Entity;

public class Pokemon extends Entity {
	
	// Standard Größe der Sprites: 33 x 32
	
	public static final int BASIS = 0;
	public static final int PHASE_1 = 1;
	public static final int PHASE_2 = 2;

	Random ran;
	
	int evolution_stage;
	
	Pokemon() {
		super();
	}
	
	protected void init() {
		ran = new Random();
		frame = 0;
		facing = Direction.SOUTH;
		spritesheet = new Texture(Utils.BASE_ASSETS_PATH + "Pokemon/" + name + ".png");
		animation = TextureRegion.split(spritesheet, 33, 32);
		currentSprite = new Sprite(animation[facing.ordinal()][frame]);
	}

	@Override
	public void dispose() {
		spritesheet.dispose();
	}
	
}
