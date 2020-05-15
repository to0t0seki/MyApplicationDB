package com.example.myapplicationdb.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ModelTable {
        @PrimaryKey
        public int modelNo;
        public String modelName;
}
