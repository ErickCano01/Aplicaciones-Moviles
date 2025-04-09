package com.example.starbuzz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StarbuzzDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "starbuzz", null, 1) {

    companion object {
        // Tabla drink
        const val TABLE_DRINK = "DRINK"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "NAME"
        const val COLUMN_DESCRIPTION = "DESCRIPTION"
        const val COLUMN_IMAGE_RESOURCE_ID = "IMAGE_RESOURCE_ID"
        const val COLUMN_FAVORITE = "FAVORITE"

        // Tabla food
        const val TABLE_FOOD = "FOOD"

        // Tabla store
        const val TABLE_STORE = "STORE"
        const val COLUMN_ADDRESS = "ADDRESS"
        const val COLUMN_HOURS = "HOURS"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // tabla drink
        db.execSQL("""
            CREATE TABLE $TABLE_DRINK (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_IMAGE_RESOURCE_ID INTEGER,
                $COLUMN_FAVORITE INTEGER)
        """)

        db.execSQL("""
    INSERT INTO $TABLE_DRINK ($COLUMN_ID, $COLUMN_NAME, $COLUMN_DESCRIPTION, 
    $COLUMN_IMAGE_RESOURCE_ID, $COLUMN_FAVORITE) VALUES
    (0, 'Latte', 'A couple of espresso shots with steamed milk', ${R.drawable.latte}, 0),
    (1, 'Cappuccino', 'Espresso, hot milk, and a steamed milk foam', ${R.drawable.cappuccino}, 0),
    (2, 'Filter', 'Highest quality beans roasted and brewed fresh', ${R.drawable.filter}, 0)
""")
        // tabla food
        db.execSQL("""
            CREATE TABLE $TABLE_FOOD (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_IMAGE_RESOURCE_ID INTEGER,
                $COLUMN_FAVORITE INTEGER,
                PRICE REAL)
        """)

        db.execSQL("""
            INSERT INTO $TABLE_FOOD ($COLUMN_ID, $COLUMN_NAME, $COLUMN_DESCRIPTION, 
            $COLUMN_IMAGE_RESOURCE_ID, $COLUMN_FAVORITE, PRICE) VALUES
            (0, 'Croissant', 'Buttery flaky pastry', ${R.drawable.croissant}, 0, 2.5),
            (1, 'Sandwich', 'Fresh bread with fillings', ${R.drawable.sandwich}, 0, 4.0),
            (2, 'Muffin', 'Sweet baked good', ${R.drawable.muffin}, 0, 3.0)
        """)

        // tabla store
        db.execSQL("""
            CREATE TABLE $TABLE_STORE (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_HOURS TEXT,
                $COLUMN_IMAGE_RESOURCE_ID INTEGER,
                $COLUMN_FAVORITE INTEGER)
        """)

        db.execSQL("""
            INSERT INTO $TABLE_STORE ($COLUMN_ID, $COLUMN_NAME, $COLUMN_ADDRESS, 
            $COLUMN_HOURS, $COLUMN_IMAGE_RESOURCE_ID, $COLUMN_FAVORITE) VALUES
            (0, 'Downtown', '123 Main St', '8:00 AM - 8:00 PM', ${R.drawable.store1}, 0),
            (1, 'Mall Branch', '456 Mall Ave', '9:00 AM - 9:00 PM', ${R.drawable.store2}, 0),
            (2, 'Airport', '789 Airport Rd', '5:00 AM - 11:00 PM', ${R.drawable.store3}, 0)
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DRINK")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STORE")
        onCreate(db)
    }
}