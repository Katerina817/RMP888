package com.example.rmp888.UI.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.rmp888.Data.DataSources.Repository.Repository;
import com.example.rmp888.Data.DataSources.Room.Entities.User;
import com.example.rmp888.UI.View.Name;

import java.util.List;
import java.util.jar.Attributes;

public class MyViewModel extends ViewModel {
    private Repository repository;

    public MyViewModel(Context context, Name stroka) {
        repository = new Repository(context,stroka);
    }
    public void writeApp(String stroka){
        repository.writeApp(stroka);
    }
    public void readAPP(){
        repository.readAPP();
    }
    public void writeCommon(String stroka){
        repository.writeCommon(stroka);
    }
    public void readCommon(){
        repository.readCommon();
    }
    public void writeString(String key, String value) {
        repository.writeString(key,value);
    }
    public void writeInt(String key, int value) {
        repository.writeInt(key,value);
    }
    public void writeBool(String key, boolean value) {
        repository.writeBool(key,value);
    }
    public String readString(String key) {
        return repository.readString(key);
    }
    public int readInt(String key) {
        return repository.readInt(key);
    }
    public boolean readBool(String key) {
        return repository.readBool(key);
    }
    public List<User> getAll() {
        return repository.getAll();
    }
    public List<User> loadAllByIds(int[] userIds) {
        return repository.loadAllByIds(userIds);
    }
    public User findByName(String first, String last) {
        return repository.findByName(first,last);
    }
    public void insertUser(User user) {
        repository.insertUser(user);
    }
    public void delete(User user) {
        repository.delete(user);
    }
}