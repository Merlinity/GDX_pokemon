package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.CoreGame;
import com.mygdx.game.EntityController;
import com.mygdx.game.Utils;

public abstract class Entity extends GameObject {
	
//	public static final int NORTH = 0;
//	public static final int SOUTH = 1;
//	public static final int WEST = 2;
//	public static final int EAST = 3;
	
	public static final int IDLE = 0;
	public static final int WALK_1 = 1;
	public static final int WALK_2 = 2;
//	public static final int SIT = ;
//	public static final int SLEEP = ;
//	public static final int RUN_1 = ;
//	public static final int RUN_2 = ;
	
	public static final String UNNAMED = "???";
	
	protected boolean walking, running, sleeping, sitting;
	protected byte walk_cycle, move_time;
	/** column of the animation in spritesheet */
	protected int frame;
	/** row of the animation in spritesheet */
	protected Direction facing;
	protected String path = Utils.BASE_ASSETS_PATH + name + ".png";
	protected Texture spritesheet;
	protected TextureRegion[][] animation;
	
	protected Entity() {
		frame = 0;
		walk_cycle = 0;
		move_time = 0;
		facing = Direction.SOUTH;
		walking = false;
		running = false;
		sitting = false;
		sleeping = false;
		init();
		if (spritesheet == null) {
			spritesheet = new Texture(Utils.BASE_ASSETS_PATH + name + ".png");
		}
		if (animation == null) {
			animation = TextureRegion.split(spritesheet, 33, 32);
		}
		if (currentSprite == null) {
			currentSprite = new Sprite(animation[Direction.SOUTH.ordinal()][frame]);
		}
		currentSprite.scale(CoreGame.windowSize);
	}
	
	public void act() {
		if (walking || running) {

			int distanceX = 1;
			int distanceY = 1;

			if (running) {
				distanceX *= 2;
				distanceY *= 2;
			}

			switch (facing) {
				case SOUTH:
					distanceY *= -1;
//					distanceX = 0;
//					break;
				case NORTH:
					distanceX = 0;
					break;
				case WEST:
					distanceX *= -1;
//					distanceY = 0;
//					break;
				case EAST:
					distanceY = 0;
					break;
				default:
					distanceX = distanceY = 0;
			}

			EntityController.get().translateEntity(distanceX, distanceY, this);

			// manage animations
			move_time++;
			if (move_time > WALK_2) {
				stopWalk();
				stopRun();

				int offsetX = this.getX() % 3;
				int offsetY = this.getY() % 3;
				if (offsetX != 0) {
//					if (distanceX < 0) {
//
//					}
					EntityController.get().teleportEntity(x+offsetX, y, this);
				}
				if (offsetY != 0) {
//					if (distanceY < 0) {

//					}
					EntityController.get().teleportEntity(x, y+offsetY, this);
				}
			}
		}

		// set the correct frame
		if (walking) {
			// set first frame of walking animation
			if (walk_cycle == 1) {
				frame = WALK_1;
				walk_cycle--;
			}
			// set second frame of walking animation
			else {
				frame = WALK_2;
				walk_cycle++;
			}
		} else if (running) {
			// TODO: there are no running animations yet
			// set first frame of walking animation
			if (walk_cycle == 1) {
				frame = WALK_1;
				walk_cycle--;
			}
			// set second frame of walking animation
			else {
				frame = WALK_2;
				walk_cycle++;
			}
		} else if (sitting) {
			frame = IDLE;
		} else {
			frame = IDLE;
		}
	}

	public void draw(SpriteBatch batch) {
		Timer.schedule(new Task() {
			public void run() {
				currentSprite.setRegion(animation[facing.ordinal()][frame]);
			}
		}, 0f);
		currentSprite.draw(batch);
	}
	
	public void walk() {
		if (!walking) {
			walking = true;
		}
	}

	public void run() {
		if (!running) {
			running = true;
		}
		walk();
	}
	
	public void stopWalk() {
		if (walking) {
			walking = false;
			move_time = 0;
		}
	}

	public void stopRun() {
		if (running) {
			running = false;
		}
	}
	
	public boolean isWalking() {
		return walking;
	}
	
	public byte getWalkTime() {
		return move_time;
	}
	
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public Direction getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		setFacing(Direction.valueOf(facing));
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
	}

}
