package com.mygdx.pong.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pong.Pong;

public class MenuState extends State {
    private Texture playButton;
    private BitmapFont text;
    private String display;

    public MenuState(GameStateManager stateManager, int winner){
        super(stateManager);
        if (winner == 1 || winner == 2) {
            String player = winner == 1 ? "Player 1 " : "Player 2 ";
            display = player.concat("won the game!\nPlay again?");
        } else {
            String player = "";
            display = "Play Pong!";
        }
        text = new BitmapFont();
        text.getData().setScale(2);
        playButton = new Texture("play.png");
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.set(new PlayState(stateManager));
            dispose();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sprites) {
        sprites.begin();
        sprites.draw(playButton, (Pong.width / 2f ) - (playButton.getWidth() / 2f),
                Pong.height / 2f );
        //draw text
        text.draw(sprites,display,(Pong.width / 2f) - 60, 400);
        sprites.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
