package ru.mirea.lebedeva.mireaproject.ui.accountposts;

import static ru.mirea.lebedeva.mireaproject.ui.auth.AuthUserActivity.nickname;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.lebedeva.mireaproject.R;


public class AccountFragment extends Fragment {

    private List<Post> posts = new ArrayList<>();
    private AccountViewModel accountViewModel;
    private PostAdapter postAdapter = new PostAdapter(posts);
    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> launcher;
    public static final String NAME = "name";
    public static final String TEXT = "text";

    private TextView profileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        profileName = rootView.findViewById(R.id.profileName);
        profileName.setText(nickname);

        if (getActivity() != null){
            accountViewModel = new ViewModelProvider(getActivity()).
                    get(AccountViewModel.class);
            accountViewModel.getPostsLiveData().observe(getActivity(), this::changeLiveData);
        }
        rootView.findViewById(R.id.addPostButton).setOnClickListener(this::onPostClick);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.postRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == 1
                            && result.getData() != null){
                        Post post = new Post();
                        post.name = result.getData().getStringExtra(NAME);
                        post.text = result.getData().getStringExtra(TEXT);
                        accountViewModel.addPost(post);
                        postAdapter.notifyDataSetChanged();
                    }
                });

        return rootView;
    }

    public void changeLiveData(List<Post> postsBase) {
        if (!posts.isEmpty()){
            posts.clear();
        }
        posts.addAll(postsBase);
        postAdapter.notifyDataSetChanged();
    }

    public void onPostClick(View view){
        Intent intent = new Intent(view.getContext(), AccountActivity.class);
        startActivity(intent);
    }
}