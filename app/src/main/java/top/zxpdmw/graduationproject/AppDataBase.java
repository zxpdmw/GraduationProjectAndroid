package top.zxpdmw.graduationproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import top.zxpdmw.graduationproject.bean.User;
import top.zxpdmw.graduationproject.dao.UserDao;

@Database(entities={User.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
}
