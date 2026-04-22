package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.components.Difficulty;
import io.github.some_example_name.screens.ScreenGame;
import io.github.some_example_name.screens.ScreenMenu;
import io.github.some_example_name.screens.ScreenRestart;

public class MyGdxGame extends Game {
    public static final int SCR_WIDTH = 2340;
    public static final int SCR_HEIGHT = 1080;

    public SpriteBatch batch;
    public OrthographicCamera camera;

    public ScreenMenu screenMenu;
    public ScreenGame screenGame;
    public ScreenRestart screenRestart;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this, Difficulty.NORMAL);
        screenRestart = new ScreenRestart(this, Difficulty.NORMAL);

        setScreen(screenMenu);
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (screenMenu != null) screenMenu.dispose();
        if (screenGame != null) screenGame.dispose();
        if (screenRestart != null) screenRestart.dispose();
    }
}
