package ru.mirea.lebedeva.mireaproject.ui.accountposts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.lebedeva.mireaproject.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {this.posts = posts;}

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent, false);

        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postName.setText(post.name);
        holder.postText.setText(post.text);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView postName;
        public TextView postText;

        public ViewHolder(View view) {
            super(view);
            postName = view.findViewById(R.id.postName);
            postText = view.findViewById(R.id.postText);
        }

    }
}
