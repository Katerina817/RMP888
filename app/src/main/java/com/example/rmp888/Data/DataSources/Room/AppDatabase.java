package com.example.rmp888.Data.DataSources.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rmp888.Data.DataSources.Room.DAO.UserDAO;
import com.example.rmp888.Data.DataSources.Room.Entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}