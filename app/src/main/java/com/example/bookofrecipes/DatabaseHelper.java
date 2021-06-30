package com.example.bookofrecipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Book_of_recipes.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "Recipes"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_ING = "Ingredients";
    public static final String COLUMN_PROC = "Cooking_process";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.openOrCreateDatabase("Рецепты", Context.MODE_PRIVATE, null);
        /*db.execSQL("CREATE TABLE IF NOT EXISTS users (" + COLUMN_NAME
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ING
                + " TEXT, " + COLUMN_PROC + " TEXT);");*/

        db.execSQL("CREATE TABLE IF NOT EXISTS Recipes (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_ING + " TEXT, " + COLUMN_PROC
                + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_ING  + ", " + COLUMN_PROC  + ") VALUES ('Первый рецепт', 'Ингредиенты для рецепта', 'Процесс приготовления');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}