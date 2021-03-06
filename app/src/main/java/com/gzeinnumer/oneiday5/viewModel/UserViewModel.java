package com.gzeinnumer.oneiday5.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gzeinnumer.oneiday5.entity.User;
import com.gzeinnumer.oneiday5.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
    public void insert(User user){
        userRepository.insert(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
}
