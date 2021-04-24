package com.example.appchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    Button btn_signin, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn_signin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        btn_register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

//        Button btn = findViewById(R.id.btn);
//        Button btn_logout = findViewById(R.id.logout);
//        Button btn_login = findViewById(R.id.login);
//        TextView txt = findViewById(R.id.txt);
//
//        if (auth.getCurrentUser() == null){
//            Toast.makeText(this, "not Login", Toast.LENGTH_SHORT).show();
//        }
//
//        btn.setOnClickListener(v -> {
//            auth.createUserWithEmailAndPassword("duchuy96itc@gmail.com", "123456")
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()){
//                            Toast.makeText(this, "Create account success!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(this, "Create account fail!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        });
//
//        btn_login.setOnClickListener(v -> {
//            auth.signInWithEmailAndPassword("duchuy96itc@gmail.com", "123456")
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()){
//                            Toast.makeText(this, "login success!  ", Toast.LENGTH_SHORT).show();
//                            txt.setText("hello " + auth.getCurrentUser().getEmail());
//                        } else {
//                            Toast.makeText(this, "logint fail!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        });
//
//        btn_logout.setOnClickListener(v -> {
//            auth.signOut();
//            if (auth.getCurrentUser() == null){
//                Toast.makeText(this, "log out", Toast.LENGTH_SHORT).show();
//                txt.setText("");
//            }
//        });
    }

    private void init() {
        btn_signin = findViewById(R.id.btn_signin);
        btn_register = findViewById(R.id.btn_register);
    }
}