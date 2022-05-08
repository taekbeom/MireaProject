package ru.mirea.lebedeva.mireaproject.ui.stories;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import ru.mirea.lebedeva.mireaproject.R;
import ru.mirea.lebedeva.mireaproject.ui.App;

public class StoryFragment extends Fragment {

    private List<Story> stories;
    private RecyclerView recyclerView;
    private Button buttonAddStory;
    private StoryDao storyDao;
    private AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stories, container, false);

        db = App.getInstance().getDatabase();
        storyDao = db.storyDao();
        stories = storyDao.getAll();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        buttonAddStory = rootView.findViewById(R.id.buttonAddStory);
        buttonAddStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(view);
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        StoryRecyclerViewAdapter storyRecyclerViewAdapter = new StoryRecyclerViewAdapter(stories);
        recyclerView.setAdapter(storyRecyclerViewAdapter);

        return rootView;
    }

    public void onClickButton(View view){
        Intent intent = new Intent(view.getContext(), StoryView.class);
        startActivity(intent);
    }
}