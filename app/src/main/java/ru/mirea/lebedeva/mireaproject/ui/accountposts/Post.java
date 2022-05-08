package ru.mirea.lebedeva.mireaproject.ui.accountposts;

import static ru.mirea.lebedeva.mireaproject.ui.auth.AuthUserActivity.nickname;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Post {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name = nickname;
    public String text;
}
