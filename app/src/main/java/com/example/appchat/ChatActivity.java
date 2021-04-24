package com.example.appchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    TextView txtHello, chat_content;
    EditText txtchat;
    Button btn_send;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chat");
    String chat;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

        txtHello.setText("hello " + user.getDisplayName() + "  " + user.getEmail());

        btn_send.setOnClickListener(v -> {
            chat = txtchat.getText().toString();
            reference.child(String.valueOf(id)).setValue(chat)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(this, "Message sent!", Toast.LENGTH_SHORT).show();
                    id++;
                } else {
                    Toast.makeText(this, "Send Message false!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> arr = (List<String>) snapshot.getValue();
                chat_content.setText(chat_content.getText().toString() + " - " + arr.get(arr.size()-1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChatActivity.this, "Failed to get value", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        txtHello = findViewById(R.id.hello);
        txtchat = findViewById(R.id.txtchat);
        btn_send = findViewById(R.id.btn_send);
        chat_content = findViewById(R.id.chat);
    }
}