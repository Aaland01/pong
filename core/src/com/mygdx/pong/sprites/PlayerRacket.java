package com.mygdx.pong.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.pong.Pong;

public class PlayerRacket {
    private Vector3 position;
    private Texture player;
    private static final int speed = 5;
    private Rectangle bounds;

    public PlayerRacket(boolean computer) {
        if(!computer){
            this.position = new Vector3(16,320,0);
        } else {
            this.position = new Vector3(Pong.width - 32,320,0);
        }
        player = new Texture("racket.png");
        bounds = new Rectangle(position.x,position.y,player.getWidth(),player.getHeight());
    }

    public void update() {
        bounds.setPosition(position.x,position.y);
    }

    public void moveUp() {
        if (position.y < Pong.height - 64) {
            position.y += speed;
        }
    }
    public void moveDown() {
        if (position.y > 0) {
            position.y -= speed;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPlayer() {
        return player;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
