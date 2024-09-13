package com.mygdx.pong.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected GameStateManager stateManager;
    protected Vector3 mouse;

    protected State(GameStateManager stateManager){
        this.stateManager = stateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch sprites);
    public abstract void dispose();
}
