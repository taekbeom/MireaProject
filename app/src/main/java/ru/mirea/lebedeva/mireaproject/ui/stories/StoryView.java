package ru.mirea.lebedeva.mireaproject.ui.stories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.lebedeva.mireaproject.MainActivity;
import ru.mirea.lebedeva.mireaproject.R;
import ru.mirea.lebedeva.mireaproject.ui.App;

public class StoryView extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private Button button;
    private AppDatabase db;
    private StoryDao storyDao;

    public static int number = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_pattern);
        db = App.getInstance().getDatabase();
        storyDao = db.storyDao();

        title = findViewById(R.id.storyTitleEdit);
        description = findViewById(R.id.storyDescriptionEdit);

        button = findViewById(R.id.addStory);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {
        number++;

        Story story = new Story();
        story.number = String.valueOf(number);
        story.title = title.getText().toString();
        story.description = description.getText().toString();

        storyDao.insert(story);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
