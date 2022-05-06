package ru.mirea.lebedeva.mireaproject.ui.stories;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String number;
    public String description;
    public String title;
}
