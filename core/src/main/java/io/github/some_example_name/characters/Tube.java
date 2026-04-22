package io.github.some_example_name.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.Difficulty;

import java.util.Random;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;
import static io.github.some_example_name.MyGdxGame.SCR_WIDTH;

public class Tube {
    Texture textureUpperTube;
    Texture textureDownTube;

    int width = 200;
    int height = 700;

    int gapHeight = 400;
    int gapY;
    int padding = 100;

    int x;
    int distanceBetweenTubes;
    int speed;

    boolean isPointReceived;
    Random random;

    public Tube(int tubeCount, int tubeIdx, Difficulty difficulty) {
        random = new Random();

        this.speed = difficulty.tubeSpeed;

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");

        isPointReceived = false;
    }

    public void move() {
        x -= speed;
        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    public boolean isHit(Bird bird) {
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width) {
            return true;
        }
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width) {
            return true;
        }
        return false;
    }

    public boolean needAddPoint(Bird bird) {
        return bird.x > x + width && !isPointReceived;
    }

    public void setPointReceived() {
        isPointReceived = true;
    }

    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }

    public void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }
}
