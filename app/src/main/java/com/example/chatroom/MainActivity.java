package com.example.chatroom;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private Button login;
    private Button sign;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        sign = findViewById(R.id.sign);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("User", new String[]{"Account=?"},account.toString(),null,null,null,null);
                if(cursor!=null&&(cursor.getColumnIndex("password")!=-1)){
                    int index = cursor.getColumnIndex("password");
                    if(password.getText().toString().equals(cursor.getString(index))){
                        Intent intent = new Intent(MainActivity.this,FriendListActivity.class);
                        intent.putExtra("account",account.getText().toString());
                        startActivity(intent);
                    }
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignActivity.class);
                startActivity(intent);
            }
        });
    }
}
