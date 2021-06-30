package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRecipe extends AppCompatActivity {

    EditText nameBox;
    EditText ingBox;
    EditText procBox;
    Button delButton;
    Button saveButton;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        nameBox = (EditText) findViewById(R.id.name);
        ingBox = (EditText) findViewById(R.id.ing);
        procBox = (EditText) findViewById(R.id.proc);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        delButton.setBackgroundColor(Color.LTGRAY);
        saveButton.setBackgroundColor(Color.LTGRAY);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            /*userCursor = db.rawQuery("select * from users" + " where " +
                    DatabaseHelper.COLUMN_NAME + "=?", new String[]{String.valueOf(userId)});*/
            userCursor.moveToFirst();
            nameBox.setText(userCursor.getString(1));
            ingBox.setText(userCursor.getString(2));

            /*String a=ingBox.getText().toString();
            a=a.replaceAll(","," ");
            a=a.replaceAll("\n"," ");
            a=a.replaceAll(":"," ");
            a=a.replaceAll(";"," ");
            a=a.replaceAll("."," ");
            a=a.replaceAll("-"," ");

            String[] b=a.split(" ");
            int i=0;
            while (true){
                if(i+1==b.length)
                {
                    break;
                }
                if (b[i].matches("1234567890")){
                    b[i]=b[i].replace(b[i],"");
                }
                i++;
            }
            a="";

            for (int j=0;j<b.length;j++){
                a+=b[i]+" ";
            }
            ingBox.setText(a);*/

            procBox.setText(userCursor.getString(3));
            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, nameBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_ING, ingBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_PROC, procBox.getText().toString());

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
        } else {
            db.insert(DatabaseHelper.TABLE, null, cv);
        }
        goHome();
    }

    public void delete(View view){
        db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }

    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}