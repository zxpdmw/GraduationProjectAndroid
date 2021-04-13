package top.zxpdmw.graduationproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import top.zxpdmw.graduationproject.bean.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("select * from user limit 1")
    User query();

    @Query("select *from user where username=:username")
    User checkUserExist(String username);
}
