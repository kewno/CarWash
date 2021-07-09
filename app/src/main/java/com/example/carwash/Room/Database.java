package com.example.carwash.Room;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Sity.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract SityDao sityDao();
}
