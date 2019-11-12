package com.example.electronic_grade_book_client_student;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private Button singInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login =  findViewById(R.id.login);
        password =  findViewById(R.id.password);
        singInButton =  findViewById(R.id.signInButton);

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(login.getText().toString(),password.getText().toString());
            }
        });

    }

    private void validate(String userName, String userPassword){
        if(userName.equals("user")&&userPassword.equals("password")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            System.out.println("blaaaaaaaaaaaaaaaaaaaad-------------");
        }
    }

}
