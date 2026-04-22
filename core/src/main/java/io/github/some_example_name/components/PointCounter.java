package io.github.some_example_name.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PointCounter {
    int x, y;
    BitmapFont font;

    public PointCounter(int x, int y) {
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);
    }

    public void draw(Batch batch, int countOfPoints) {
        font.draw(batch, "Points: " + countOfPoints, x, y);
    }

    public void drawWithRecord(Batch batch, int countOfPoints, int highScore) {
        font.draw(batch, "Points: " + countOfPoints, x, y);
        font.draw(batch, "Record: " + highScore, x, y - 100);
    }

    public void drawMenuRecord(Batch batch, int highScore) {
        font.draw(batch, "Record: " + highScore, x, y);
    }

    public void drawRecordInGame(Batch batch, int highScore, int x, int y) {
        font.draw(batch, "Record: " + highScore, x, y);
    }

    public void dispose() {
        font.dispose();
    }
}
