package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Utils;

public class Ethan extends Entity {

    public Ethan() {
        super(-1);
    }

    protected void init() {
        name = "ethan";
        frame = 0;
        facing = Direction.SOUTH;
        spritesheet = new Texture(Utils.BASE_ASSETS_PATH + name + ".png");
        animation = TextureRegion.split(spritesheet, 33, 32);
        currentSprite = new Sprite(animation[facing.ordinal()][frame]);
    }

    @Override
    public void dispose() {
        spritesheet.dispose();
    }
}
