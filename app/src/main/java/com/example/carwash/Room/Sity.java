package com.example.carwash.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String sityName;
}
