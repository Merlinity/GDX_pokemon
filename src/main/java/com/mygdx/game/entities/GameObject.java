package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    // the sprite to display
    protected Sprite currentSprite;

    // x and y location on the map
    protected int x, y;

    // whether objects on the same layer can pass through or not
    protected boolean solid;


    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(SpriteBatch batch);

    abstract protected void init();

    abstract public void dispose();
}
