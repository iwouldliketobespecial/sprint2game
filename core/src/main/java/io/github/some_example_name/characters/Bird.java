package io.github.some_example_name.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.some_example_name.MyGdxGame;

public class Bird {
    int x, y;
    int speed;
    int width = 173;
    int height = 128;
    Texture[] framesArray;
    int frameCounter;
    int frameMultiplier = 10;

    boolean jump;
    int jumpHeight;
    final int maxHeightOfJump = 150;

    public Bird(int x, int y, Texture[] framesArray, int speed) {
        this.x = x;
        this.y = y;
        this.framesArray = framesArray;
        this.speed = speed;
        this.frameCounter = 0;
        this.jump = false;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void draw(Batch batch) {
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > MyGdxGame.SCR_HEIGHT) return false;
        return true;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }
}
