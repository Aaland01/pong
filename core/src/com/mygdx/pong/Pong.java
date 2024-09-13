package com.mygdx.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pong.States.GameStateManager;
import com.mygdx.pong.States.MenuState;

public class Pong extends ApplicationAdapter {

	public static final int width = 800;
	public static final	int height = 480;
	Texture img;

	private GameStateManager stateManager;
	private SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager = new GameStateManager();
		stateManager.push(new MenuState(stateManager, 0));
	}

	@Override
	public void render () {
		ScreenUtils.clear(20f/255f,20f/255f,20f/255f,100f/255f);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);


	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
