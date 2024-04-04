package com.example.rmp888.Data.DataSources.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import com.example.rmp888.Data.DataSources.Files.AppSpDS;
import com.example.rmp888.Data.DataSources.Files.CommonFDS;
import com.example.rmp888.Data.DataSources.Room.AppDatabase;
import com.example.rmp888.Data.DataSources.Room.DAO.UserDAO;
import com.example.rmp888.Data.DataSources.Room.Entities.User;
import com.example.rmp888.Data.DataSources.SP.SPDS;
import com.example.rmp888.UI.View.Name;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Repository {
    private AppDatabase database;
    private AppSpDS app;
    private CommonFDS common;
    private SPDS shared;
    private UserDAO userDao;
    public Repository(Context context, Name stroka) {
        if(stroka==Name.APP){
            String fileName = "App.txt";
            this.app=new AppSpDS(context,fileName);
        }
        if(stroka==Name.COMMON){
            String fileName = "Common.txt";
            this.common=new CommonFDS(context,fileName);
        }
        if(stroka==Name.SHARED){
            String fileName = "SharedPref";
            this.shared=new SPDS(context,fileName);
        }
        if(stroka==Name.DBASE){
            if (databaseExists(context, "database4")) {
                deleteDatabase(context, "database4");
            }
            this.database = Room.databaseBuilder(context, AppDatabase.class, "database4").build();
            this.userDao = database.userDao();
        }
    }
    public void writeApp(String stroka){
        app.writeApp(stroka);
    }
    public void readAPP(){
        app.readAPP();
    }
    public void writeCommon(String stroka){
        common.writeCommon(stroka);
    }
    public void readCommon(){
        common.readCommon();
    }
    public void writeString(String key, String value) {
        shared.writeString(key,value);
    }
    public void writeInt(String key, int value) {
        shared.writeInt(key,value);
    }
    public void writeBool(String key, boolean value) {
        shared.writeBool(key,value);
    }
    public String readString(String key) {
        return shared.readString(key);
    }
    public int readInt(String key) {
        return shared.readInt(key);
    }
    public boolean readBool(String key) {
        return shared.readBool(key);
    }
    private void deleteDatabase(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        if (dbFile.delete()) {
            Log.i("Repository", "Старая база данных удалена: " + dbName);
        } else {
            Log.w("Repository", "Не удалось удалить старую базу данных: " + dbName);
        }
    }
    private boolean databaseExists(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
    public List<User> getAll() {
        if(userDao!=null){
            Log.i("Repository",userDao.getAll().get(0).firstName);
            Log.i("Repository",userDao.getAll().get(0).lastName);
            return userDao.getAll();
        }
        return null;
    }
    public List<User> loadAllByIds(int[] userIds) {
        if(userDao!=null){
            return userDao.loadAllByIds(userIds);
        }
        return null;
    }
    public User findByName(String first, String last) {
        if(userDao!=null){
            return userDao.findByName(first,last);
        }
        return null;
    }
    public void insertUser(User user) {
        if(userDao!=null){
            userDao.insertUser(user);
        }
    }
    public void delete(User user) {
        if(userDao!=null){
            userDao.delete(user);
        }
    }
}