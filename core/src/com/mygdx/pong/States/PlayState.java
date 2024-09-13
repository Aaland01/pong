package com.mygdx.pong.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pong.Pong;
import com.mygdx.pong.sprites.Ball;
import com.mygdx.pong.sprites.PlayerRacket;

public class PlayState extends State{

    private PlayerRacket playerRacket;
    private Ball ball;
    private PlayerRacket oppRacket;
    private BitmapFont font;
    private Rectangle upperBounds;
    private Rectangle lowerBounds;

    private int scoreA;
    private int scoreB;
    private static final int endScore = 21;

    private int hitCounter;
    private float computerWait;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);
        playerRacket = new PlayerRacket(false);
        ball = new Ball();
        oppRacket = new PlayerRacket(true);
        camera.setToOrtho(false);
        font = new BitmapFont();
        font.getData().setScale(4);

        lowerBounds = new Rectangle(-1,-1,Pong.width + 2,1);
        upperBounds = new Rectangle(-1,Pong.height + 1,Pong.width + 2,1);

        hitCounter = 0;
        computerWait = (Pong.width / 2f ) - 50;
    }

    @Override
    protected void handleInput() {
        float relativeTouchPos = Math.abs(Gdx.input.getY() - Pong.height);
        float racketPos = playerRacket.getPosition().y;
        float buffer = 64;
        if ( relativeTouchPos > (racketPos) ){
            playerRacket.moveUp();
        } if ( (relativeTouchPos - buffer) < (racketPos) ){
            playerRacket.moveDown();
        }
        playerRacket.update();
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        computerInput();
        collisionCheck();
        checkScore();
    }

    private void computerInput() {
        if (ball.getPosition().x >= computerWait) {
            float ballPos = ball.getPosition().y;
            float oppRacketHeight = oppRacket.getPosition().y;
            float buffer = 10;
            if ( ballPos > (oppRacketHeight) ){
                oppRacket.moveUp();
            } if ( ballPos - buffer < (oppRacketHeight) ){
                oppRacket.moveDown();
            }
            oppRacket.update();
        }
    }

    private void checkScore(){
        if (ball.getPosition().x <= 0) {
            scoreB += 1;
            resetBall();
        } if (ball.getPosition().x + 16 >= 800) {
            scoreA += 1;
            resetBall();
        } if ((scoreA == endScore) || (scoreB == endScore)){
            endGame(scoreA == endScore ? 1 : 2);
        } else if (hitCounter>5){
            ball.speedUp();
            this.hitCounter = 0;
        }
    }

    private void collisionCheck() {
        if (ball.collision(playerRacket.getBounds()) || ball.collision(oppRacket.getBounds())) {
            //ball move right/left
            hitCounter++;
            ball.leftRight();
        } if (ball.collision(upperBounds) || ball.collision(lowerBounds)) {
            //ball move down/up
            ball.upDown();
        }ball.update();
    }

    private void resetBall() {

        this.ball = new Ball();
        this.hitCounter = 0;
    }

    private void endGame(int winner) {
        stateManager.set(new MenuState(stateManager, winner));
    }


    @Override
    public void render(SpriteBatch sprites) {
        sprites.setProjectionMatrix(camera.combined);
        sprites.begin();
        sprites.draw(playerRacket.getPlayer(),playerRacket.getPosition().x,playerRacket.getPosition().y);
        sprites.draw(ball.getTexture(),ball.getPosition().x,ball.getPosition().y);
        sprites.draw(oppRacket.getPlayer(), oppRacket.getPosition().x, oppRacket.getPosition().y);
        font.draw(sprites, "Score: ".concat(Integer.toString(scoreA))
                .concat(" | ").concat(Integer.toString(scoreB))
                ,Pong.width / 2f - 150,Pong.height - 50);
        sprites.end();
    }

    @Override
    public void dispose() {
        playerRacket.getPlayer().dispose();
        oppRacket.getPlayer().dispose();
        ball.getTexture().dispose();
    }
}
