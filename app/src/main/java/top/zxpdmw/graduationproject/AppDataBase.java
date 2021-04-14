package top.zxpdmw.graduationproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.dao.UserDao;


public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
}
