package com.example.appchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    EditText txtuser, txtpwd;
    Button btn_login;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String user, pwd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btn_login.setOnClickListener(v -> {
            user = txtuser.getText().toString();
            pwd = txtpwd.getText().toString();
            auth.signInWithEmailAndPassword(user, pwd)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, ChatActivity.class));
                        } else {
                            Toast.makeText(this, "Login fail!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

    private void init() {
        txtuser = findViewById(R.id.username);
        txtpwd = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
    }

}