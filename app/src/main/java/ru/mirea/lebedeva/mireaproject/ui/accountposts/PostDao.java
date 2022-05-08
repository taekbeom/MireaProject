package ru.mirea.lebedeva.mireaproject.ui.accountposts;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDao {
    @Query("SELECT * FROM post")
    LiveData<List<Post>> getAllPosts();
    @Insert
    void insert(Post post);
    @Update
    void update(Post post);
    @Delete
    void delete(Post post);
}
