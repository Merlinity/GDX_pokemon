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
    private static final int FRAME_SWITCH = 5;
    protected Random ran;


    private int frameTime;

    Pokemon(int id) {
        super(id);
    }

    @Override
    protected void init() {
        name = Pkmns.getName(id);
        ran = new Random();
        frame = 0;
        facing = Direction.SOUTH;
        spritesheet = new Texture(Utils.BASE_ASSETS_PATH + "Pokemon/" + id + ".png");
        animation = TextureRegion.split(spritesheet, 33, 32);
        currentSprite = new Sprite(animation[facing.ordinal()][frame]);
    }

    @Override
    public void dispose() {
        spritesheet.dispose();
    }

    @Override
    public void idleAction() {
        this.frameTime++;
        if (frameTime > FRAME_SWITCH) {
            frameTime = 0;
            if (frame == IDLE) {
                frame = 1;
            } else {
                frame = IDLE;
            }
        }
    }
}
