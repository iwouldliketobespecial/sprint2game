package io.github.some_example_name.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class RecordManager {
    private static final String RECORD_FILE = "record.txt";
    private int highScore;

    public RecordManager() {
        loadRecord();
    }

    private void loadRecord() {
        FileHandle file = Gdx.files.local(RECORD_FILE);
        if (file.exists()) {
            highScore = Integer.parseInt(file.readString());
        } else {
            highScore = 0;
        }
    }

    private void saveRecord() {
        FileHandle file = Gdx.files.local(RECORD_FILE);
        file.writeString(String.valueOf(highScore), false);
    }

    public boolean checkAndSetRecord(int score) {
        if (score > highScore) {
            highScore = score;
            saveRecord();
            return true;
        }
        return false;
    }

    public int getHighScore() {
        return highScore;
    }
}
