package com.example.carwash.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SityDao {
    @Query("Select * From sity")
    List<Sity> getAll();

    @Insert
    void insert(Sity sity);

    @Update
    void update(Sity sity);

    @Delete
    void delete(Sity sity);
}
