package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.characters.Tube;
import io.github.some_example_name.components.Difficulty;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.RecordManager;

public class ScreenGame implements Screen {
    private MyGdxGame myGdxGame;
    private Bird bird;
    private Tube[] tubes;
    private int tubeCount = 3;
    private boolean isGameOver;
    private int gamePoints;
    private PointCounter pointCounter;
    private MovingBackground background;
    private RecordManager recordManager;
    private Difficulty difficulty;

    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    public ScreenGame(MyGdxGame myGdxGame, Difficulty difficulty) {
        this.myGdxGame = myGdxGame;
        this.difficulty = difficulty;

        Texture[] birdFrames = new Texture[]{
            new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png")
        };

        bird = new Bird(100, 300, birdFrames, difficulty.birdSpeed);
        initTubes();
        pointCounter = new PointCounter(
            MyGdxGame.SCR_WIDTH - pointCounterMarginRight,
            MyGdxGame.SCR_HEIGHT - pointCounterMarginTop
        );
        background = new MovingBackground("backgrounds/game_bg.png");
        recordManager = new RecordManager();
    }

    private void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i, difficulty);
        }
    }

    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
        bird.setY(MyGdxGame.SCR_HEIGHT / 2);
        initTubes();
    }

    @Override
    public void render(float delta) {
        if (!isGameOver) {
            if (Gdx.input.justTouched()) {
                bird.onClick();
            }

            background.move();
            bird.fly();

            if (!bird.isInField()) {
                recordManager.checkAndSetRecord(gamePoints);
                myGdxGame.screenRestart.gamePoints = gamePoints;
                myGdxGame.screenRestart.highScore = recordManager.getHighScore();
                isGameOver = true;
                myGdxGame.setScreen(myGdxGame.screenRestart);
            }

            for (Tube tube : tubes) {
                tube.move();

                if (tube.isHit(bird)) {
                    recordManager.checkAndSetRecord(gamePoints);
                    myGdxGame.screenRestart.gamePoints = gamePoints;
                    myGdxGame.screenRestart.highScore = recordManager.getHighScore();
                    isGameOver = true;
                    myGdxGame.setScreen(myGdxGame.screenRestart);
                } else if (tube.needAddPoint(bird)) {
                    gamePoints++;
                    tube.setPointReceived();
                    recordManager.checkAndSetRecord(gamePoints);
                    System.out.println(gamePoints);
                }
            }
        }

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);
        pointCounter.drawRecordInGame(myGdxGame.batch, recordManager.getHighScore(),
            MyGdxGame.SCR_WIDTH - 400, MyGdxGame.SCR_HEIGHT - 160);

        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube tube : tubes) tube.dispose();
        pointCounter.dispose();
        background.dispose();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
