package ru.mirea.lebedeva.mireaproject.ui.stories;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ru.mirea.lebedeva.mireaproject.R;

import java.util.List;

public class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryRecyclerViewAdapter.ViewHolder> {

    private List<Story> stories;

    public StoryRecyclerViewAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.storyId.setText(story.number);
        holder.storyTitle.setText(story.title);
        holder.storyDescription.setText(story.description);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView storyId;
        public TextView storyTitle;
        public TextView storyDescription;

        public ViewHolder(View view) {
            super(view);
            storyId = view.findViewById(R.id.storyId);
            storyTitle = view.findViewById(R.id.storyTitle);
            storyDescription = view.findViewById(R.id.storyDescription);
        }

    }
}