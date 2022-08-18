package com.example.chatroom;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignActivity extends AppCompatActivity {
    private EditText SAccount;
    private EditText SPassword;
    private EditText ConfirmPassword;
    private Button SubmitButton;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);

        SAccount = findViewById(R.id.sAccount);
        SPassword = findViewById(R.id.sPassword);
        ConfirmPassword = findViewById(R.id.confirm);
        SubmitButton = findViewById(R.id.sSubmit);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,1);


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SPassword.getText().toString().equals(ConfirmPassword.getText().toString())){
                    Toast.makeText(SignActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    SPassword.setText("");
                    ConfirmPassword.setText("");
                }else{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("Account",Integer.parseInt(SAccount.getText().toString()));
                    values.put("Password", SPassword.getText().toString());
                    db.insert("User",null,values);
                    Intent intent = new Intent(SignActivity.this,FriendListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
