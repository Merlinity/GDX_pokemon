package com.mygdx.game.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.CoreGame;
import com.mygdx.game.Utils;

public abstract class Entity {
	
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int WEST = 2;
	public static final int EAST = 3;
	
	public static final int IDLE = 0;
	public static final int WALK_1 = 1;
	public static final int WALK_2= 2;
//	public static final int SIT = ;
//	public static final int SLEEP = ;
//	public static final int RUN_1 = ;
//	public static final int RUN_2 = ;
	
	public static final String UNNAMED = "???";
	
	protected boolean walking, running, sleeping, sitting;
	protected byte walk_cycle, walk_time;
	protected int frame, facing, x, y;
	protected String name = "badlogic";
	protected String path = Utils.BASE_ASSETS_PATH + name + ".png";
	protected Texture spritesheet;
	protected TextureRegion[][] animation;
	protected Sprite currentSprite;
	
	protected Entity() {
		frame = 0;
		walk_cycle = 0;
		walk_time = 0;
		facing = SOUTH;
		walking = false;
		running = false;
		sitting = false;
		sleeping = false;
		init();
		if (spritesheet == null)
			spritesheet = new Texture(Utils.BASE_ASSETS_PATH+name+".png");
		if (animation == null)
			animation = TextureRegion.split(spritesheet, 33, 30);
		if (currentSprite == null)
			currentSprite = new Sprite(animation[SOUTH][frame]);
		currentSprite.scale(CoreGame.size);
	}
	
	public void tick() {
		if (walking || running) {
			switch (facing) {
			case NORTH:	currentSprite.translateY(7*CoreGame.size);	break;
			case SOUTH:	currentSprite.translateY(-7*CoreGame.size);	break;
			case EAST:	currentSprite.translateX(7*CoreGame.size);	break;
			case WEST:	currentSprite.translateX(-7*CoreGame.size);	break;
			}
		}
		
		if (walking) {
			walk_time++;
			if (walk_time > 2) {
				stopWalk();
			}
			if (walk_cycle == 1) {
				frame = WALK_1;
				walk_cycle--;
			} else {
				frame = WALK_2;
				walk_cycle++;
			}
		} else if (running) {
			
		} else if (sitting) {
			
		} else {
			frame = IDLE;
		}
	}
	
	public void draw(SpriteBatch batch) {
		Timer.schedule(new Task() {
			public void run() {
				currentSprite.setRegion(animation[facing][frame]);
			}
		}, 0f);
		currentSprite.draw(batch);
	}
	
	abstract protected void init();
	
	abstract public void dispose();
	
	public void walk() {
		walking = true;
	}
	
	public void stopWalk() {
		walking = false;
		walk_time = 0;
	}
	
	public boolean isWalking() {
		return walking;
	}
	
	public byte getWalkTime() {
		return walk_time;
	}
	
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Sprite getCurrentSprite() {
		return currentSprite;
	}
}
