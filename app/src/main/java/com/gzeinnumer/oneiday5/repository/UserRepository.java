package com.gzeinnumer.oneiday5.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.gzeinnumer.oneiday5.dao.UserDao;
import com.gzeinnumer.oneiday5.entity.User;
import com.gzeinnumer.oneiday5.room.AppDatabase;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        userDao = database.userDao();
        allUsers = userDao.getAll();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public void insert(User user){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });
    }

    public void delete(User user){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delete(user);
            }
        });
    }
}
