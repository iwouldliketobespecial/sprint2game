package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.Difficulty;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.TextButton;

public class ScreenRestart implements Screen {
    private MyGdxGame myGdxGame;
    private MovingBackground background;
    private TextButton buttonRestart;
    private TextButton buttonMenu;
    private PointCounter pointCounter;
    private Difficulty currentDifficulty;
    public int gamePoints;
    public int highScore;

    public ScreenRestart(MyGdxGame myGdxGame, Difficulty difficulty) {
        this.myGdxGame = myGdxGame;
        this.currentDifficulty = difficulty;
        this.gamePoints = 0;
        this.highScore = 0;

        background = new MovingBackground("backgrounds/restart_bg.png");
        buttonRestart = new TextButton(100, 500, "Restart");
        buttonMenu = new TextButton(100, 300, "Menu");
        pointCounter = new PointCounter(750, 530);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);

            int touchX = (int) touch.x;
            int touchY = (int) touch.y;

            if (buttonRestart.isHit(touchX, touchY)) {
                myGdxGame.setScreen(new ScreenGame(myGdxGame, currentDifficulty));
            }

            if (buttonMenu.isHit(touchX, touchY)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);
        buttonMenu.draw(myGdxGame.batch);
        pointCounter.drawWithRecord(myGdxGame.batch, gamePoints, highScore);

        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        buttonRestart.dispose();
        buttonMenu.dispose();
        pointCounter.dispose();
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
