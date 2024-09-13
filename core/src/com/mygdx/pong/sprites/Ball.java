package com.mygdx.pong.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.pong.Pong;

import java.util.Random;

public class Ball {
    private float speedScalar;
    private boolean goingDown;
    private boolean goingLeft;
    private Vector3 position;

    private Texture ball;
    private Rectangle bounds;

    public Ball() {
        ball = new Texture("ball.png");
        position = new Vector3((Pong.width / 2f),(Pong.height / 2f),0);

        Random random = new Random();
        goingDown = random.nextBoolean();
        goingLeft = random.nextBoolean();
        speedScalar = 3.5f;

        bounds = new Rectangle(position.x,position.y,ball.getWidth(), ball.getHeight());
    }

    public void update() {
        float directionX = goingLeft ? -1 : 1;
        float directionY = goingDown ? -1 : 1;
        Vector3 movement = new Vector3(directionX,directionY,0)
                .scl(speedScalar);
        position.add(movement);
        bounds.setPosition(position.x,position.y);

    }

    public boolean collision(Rectangle racket) {
        return racket.overlaps(bounds);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return ball;
    }

    public void upDown(){
        this.goingDown = !goingDown;
    }

    public void leftRight(){
        this.goingLeft = !goingLeft;
    }

    public void speedUp() {
        speedScalar = speedScalar * 1.5f;
    }
}
