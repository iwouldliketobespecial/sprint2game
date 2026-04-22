package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.Difficulty;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.RecordManager;
import io.github.some_example_name.components.TextButton;

public class ScreenMenu implements Screen {
    private MyGdxGame myGdxGame;
    private MovingBackground background;
    private TextButton buttonStart;
    private TextButton buttonExit;
    private TextButton buttonNormal;
    private TextButton buttonHard;
    private TextButton buttonExpert;
    private RecordManager recordManager;
    private PointCounter pointCounter;
    private Difficulty currentDifficulty;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        this.currentDifficulty = Difficulty.NORMAL;

        background = new MovingBackground("backgrounds/menu_bg.png");

        buttonStart = new TextButton(100, 500, "Start");
        buttonExit = new TextButton(100, 300, "Exit");

        buttonNormal = new TextButton(1800, 650, "Normal");
        buttonHard = new TextButton(1800, 450, "Hard");
        buttonExpert = new TextButton(1800, 250, "Expert");

        recordManager = new RecordManager();
        pointCounter = new PointCounter(100, 200);

        buttonNormal.setColor(Color.GREEN);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);

            int touchX = (int) touch.x;
            int touchY = (int) touch.y;

            if (buttonStart.isHit(touchX, touchY)) {
                myGdxGame.setScreen(new ScreenGame(myGdxGame, currentDifficulty));
            }

            if (buttonExit.isHit(touchX, touchY)) {
                Gdx.app.exit();
            }

            if (buttonNormal.isHit(touchX, touchY)) {
                currentDifficulty = Difficulty.NORMAL;
                buttonNormal.setColor(Color.GREEN);
                buttonHard.setColor(Color.WHITE);
                buttonExpert.setColor(Color.WHITE);
            }
            if (buttonHard.isHit(touchX, touchY)) {
                currentDifficulty = Difficulty.HARD;
                buttonNormal.setColor(Color.WHITE);
                buttonHard.setColor(Color.GREEN);
                buttonExpert.setColor(Color.WHITE);
            }
            if (buttonExpert.isHit(touchX, touchY)) {
                currentDifficulty = Difficulty.EXPERT;
                buttonNormal.setColor(Color.WHITE);
                buttonHard.setColor(Color.WHITE);
                buttonExpert.setColor(Color.GREEN);
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        buttonNormal.draw(myGdxGame.batch);
        buttonHard.draw(myGdxGame.batch);
        buttonExpert.draw(myGdxGame.batch);
        pointCounter.drawMenuRecord(myGdxGame.batch, recordManager.getHighScore());

        myGdxGame.batch.end();
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
    @Override
    public void dispose() {
        background.dispose();
        buttonStart.dispose();
        buttonExit.dispose();
        buttonNormal.dispose();
        buttonHard.dispose();
        buttonExpert.dispose();
        pointCounter.dispose();
    }
}
