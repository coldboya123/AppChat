package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    EditText txtuser, txtpwd, txtrepwd, txtname;
    Button btn_register;
    String user, pwd, repwd, name;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    UserProfileChangeRequest profileChangeRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btn_register.setOnClickListener(v -> {
            name = txtname.getText().toString();
            user = txtuser.getText().toString();
            pwd = txtpwd.getText().toString();
            repwd = txtrepwd.getText().toString();
            if (!pwd.equals(repwd)) {
                Toast.makeText(this, "Password no match!", Toast.LENGTH_SHORT).show();
            } else {
                auth.createUserWithEmailAndPassword(user, pwd)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Register success!", Toast.LENGTH_SHORT).show();
                                profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                                auth.getCurrentUser().updateProfile(profileChangeRequest);
                                startActivity(new Intent(this, LoginActivity.class));
                            } else {
                                Toast.makeText(this, "Register fail!  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

    private void init() {
        txtuser = findViewById(R.id.username);
        txtpwd = findViewById(R.id.password);
        txtrepwd = findViewById(R.id.repassword);
        txtname = findViewById(R.id.name);
        btn_register = findViewById(R.id.register);
    }
}