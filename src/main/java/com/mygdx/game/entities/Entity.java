package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.CoreGame;
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

	protected int velocityX, velocityY;
	
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

	/**
	 * Process current state and determine what the next action should be.
	 */
	public void determineNextAction() {
//		if (move_time > WALK_2) {
//			stopWalk();
//			stopRun();
//		}
		if (nextAction == ObjectAction.MOVE) {

			int distanceX = 8;
			int distanceY = 8;

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

			velocityX = distanceX;
			velocityY = distanceY;

//			EntityController.get().translateEntity(distanceX, distanceY, this);

			// manage animations
			move_time++;
		}

		// set the correct frame
		if (walking) {
			// do nothing
		} else if (sitting) {
			frame = IDLE;
		} else {
			frame = IDLE;
		}
	}


	public void executeMove() {
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
	}

	public void draw(SpriteBatch batch) {
		Timer.schedule(new Task() {
			public void run() {
				currentSprite.setRegion(animation[facing.ordinal()][frame]);
			}
		}, 0f);
		currentSprite.draw(batch);
	}
	
	public void queueWalk() {
		if (!walking) {
			walking = true;
		}
		if (nextAction != ObjectAction.MOVE) {
			nextAction = ObjectAction.MOVE;
		}
	}

	public void queueRun() {
		if (!running) {
			running = true;
		}
		queueWalk();
	}
	
	public void stopWalk() {
		if (walking) {
			walking = false;
		}
		if (nextAction == ObjectAction.MOVE) {
			nextAction = ObjectAction.IDLE;
			velocityX = 0;
			velocityY = 0;
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

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public ObjectAction getNextAction() {
		return nextAction;
	}
}
