package ru.mirea.lebedeva.mireaproject.ui.accountposts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.lebedeva.mireaproject.ui.App;
import ru.mirea.lebedeva.mireaproject.ui.stories.AppDatabase;

public class AccountViewModel extends ViewModel {
    private final LiveData<List<Post>> posts;
    private final AppDatabase appDatabase = App.instance.getDatabase();
    private final PostDao postDao = appDatabase.postDao();

    public AccountViewModel(){
        posts = postDao.getAllPosts();
    }

    public void addPost(Post post){
        postDao.insert(post);
    }

    public LiveData<List<Post>> getPostsLiveData(){
        return posts;
    }

    public List<Post> getPosts(){
        return posts.getValue();
    }

}
