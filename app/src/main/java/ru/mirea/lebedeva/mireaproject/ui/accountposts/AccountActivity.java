package ru.mirea.lebedeva.mireaproject.ui.accountposts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.lebedeva.mireaproject.MainActivity;
import ru.mirea.lebedeva.mireaproject.R;
import ru.mirea.lebedeva.mireaproject.ui.App;
import ru.mirea.lebedeva.mireaproject.ui.stories.AppDatabase;

public class AccountActivity extends AppCompatActivity {
    private EditText postText;
    private Button addPost;

    private AppDatabase db;
    private PostDao postDao;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_pattern);

        db = App.getInstance().getDatabase();
        postDao = db.postDao();

        postText = findViewById(R.id.postTextField);

        addPost = findViewById(R.id.postButton);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBtn(view);
            }
        });
    }

    public void saveBtn(View view) {

        Post post = new Post();
        post.text = postText.getText().toString();

        postDao.insert(post);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
