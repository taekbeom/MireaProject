package ru.mirea.lebedeva.mireaproject.ui.stories;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.lebedeva.mireaproject.ui.accountposts.Post;
import ru.mirea.lebedeva.mireaproject.ui.accountposts.PostDao;

@Database(entities = {Story.class, Post.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase{
    public abstract StoryDao storyDao();
    public abstract PostDao postDao();
}