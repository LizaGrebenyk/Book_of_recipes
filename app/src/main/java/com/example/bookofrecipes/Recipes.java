package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Recipes extends AppCompatActivity {

    ListView userList;
    TextView textView;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Button button;
    EditText nmFtr;
    EditText ingFtr;
    Button nmSrch;
    Button ingSrch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        userList =findViewById(R.id.List);
        textView=findViewById(R.id.text);
        button=findViewById(R.id.search);
        nmFtr=findViewById(R.id.NameFilter);
        ingFtr=findViewById(R.id.IngFilter);
        nmSrch=findViewById(R.id.NameSearch);
        ingSrch=findViewById(R.id.IngSearch);

        button.setBackgroundColor(Color.LTGRAY);
        nmSrch.setBackgroundColor(Color.LTGRAY);
        ingSrch.setBackgroundColor(Color.LTGRAY);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddRecipe.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                nmFtr.setVisibility(View.VISIBLE);
                ingFtr.setVisibility(View.VISIBLE);
                nmSrch.setVisibility(View.VISIBLE);
                ingSrch.setVisibility(View.VISIBLE);

                /*
                // если в текстовом поле есть текст, выполняем фильтрацию
                // данная проверка нужна при переходе от одной ориентации экрана к другой
                if(!nmFtr.getText().toString().isEmpty())
                    userAdapter.getFilter().filter(nmFtr.getText().toString());

                // установка слушателя изменения текста
                nmFtr.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) { }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    // при изменении текста выполняем фильтрацию
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        userAdapter.getFilter().filter(s.toString());
                    }
                });

                if(!ingFtr.getText().toString().isEmpty()){
                    String a=ingFtr.getText().toString();
                    a=a.replaceAll(" ","");
                    userAdapter.getFilter().filter(a);}
                    userAdapter.getFilter().filter(ingFtr.getText().toString());}

                ingFtr.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) { }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        if (s.toString().contains(" ") || s.toString().contains(", ") ||
                                s.toString().contains(" ," )|| s.toString().contains("\n") ||
                                s.toString().contains(",") || s.toString().contains(":") || s.toString().contains("."))
                        {
                            s=s.toString().replaceAll(" ","");
                            s=s.toString().replaceAll(", ","");
                            s=s.toString().replaceAll(" ,","");
                            s=s.toString().replaceAll(",","");
                            s=s.toString().replaceAll("\n","");
                            s=s.toString().replaceAll(":","");
                            s=s.toString().replaceAll(".","");
                        }

                        userAdapter.getFilter().filter(s.toString());
                    }
                });

                // устанавливаем провайдер фильтрации
                userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {

                        if (constraint == null || constraint.length() == 0) {

                            return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                        } else if (!nmFtr.getText().toString().isEmpty()) {
                            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                    DatabaseHelper.COLUMN_NAME + " like ?", new String[]{"%" + constraint.toString() + "%"});
                            textView.setText("Найдено рецептов: " + userCursor.getCount());
                            return userCursor;
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                    DatabaseHelper.COLUMN_NAME + " like ?", new String[]{"%" + constraint.toString() + "%"} );
                        } else if (!ingFtr.getText().toString().isEmpty()) {
                            String[] ingArr = ingFtr.getText().toString().split(" ");
                            for (int i = 0; i < ingArr.length; i++) {
                                userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                        DatabaseHelper.COLUMN_ING + " like ?", new String[]{"%" + ingArr[i].toString() + "%"});
                                textView.setText("Найдено рецептов: " + userCursor.getCount());
                                return userCursor;
                            }
                            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                    DatabaseHelper.COLUMN_ING + " like ?", new String[]{"%" + constraint.toString() + "%"} );
                            textView.setText("Найдено рецептов: " + userCursor.getCount());
                            return userCursor;
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                    DatabaseHelper.COLUMN_ING + " like ?", new String[]{"%" + constraint.toString() + "%"} );
                        }
                        else {
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                        }
                        return userCursor;
                    }
                });
                //textView.setText("Найдено рецептов: " + userCursor.getCount());
                */
            }
        });

        nmSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nmFtr.getText().toString().isEmpty()){
                    userAdapter.getFilter().filter(nmFtr.getText().toString());}

                userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {

                        if (constraint == null || constraint.length() == 0) {
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                        }
                        else if (!nmFtr.getText().toString().isEmpty()) {
                            Cursor cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                    DatabaseHelper.COLUMN_NAME + " like ?", new String[]{"%" + constraint.toString() + "%"});
                            textView.setText("Найдено рецептов: " + cursor.getCount());
                            return cursor;
                        }
                        else{
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                        }
                    }
                });
            }
        });

        ingSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ingFtr.getText().toString().isEmpty()){
                    userAdapter.getFilter().filter(ingFtr.getText().toString());}

                userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {

                        if (constraint == null || constraint.length() == 0) {
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                        }
                        else if (!ingFtr.getText().toString().isEmpty()) {
                                    /*String[] ingArr = ingFtr.getText().toString().split(" ");
                                    for (String s : ingArr) {
                                        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                                DatabaseHelper.COLUMN_ING + " like ?", new String[]{"%" + s + "%"});
                                        return userCursor;
                                    }*/
                            Cursor cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                    DatabaseHelper.COLUMN_ING + " like ?", new String[]{"%" + constraint.toString() + "%"});
                            textView.setText("Найдено рецептов: " + cursor.getCount());
                            return cursor;
                        }
                        else{
                            return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                        }
                    }
                });
            }
        });

        //databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        //db=openOrCreateDatabase("Book_of_recipes.db",MODE_PRIVATE,null);
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
        //userCursor = db.rawQuery("select * from recipes;", null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[]{DatabaseHelper.COLUMN_NAME};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                userCursor, headers, new int[]{android.R.id.text1}, 0);
        textView.setText("Найдено рецептов: " + userCursor.getCount());
        userList.setAdapter(userAdapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
}