package com.example.tasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button loginBTN;
    TextView createAccount;
    private EditText emailET,passET;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createAccount=findViewById(R.id.create_account);
        createAccount.setOnClickListener(c -> {
            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(i);
            finish();
        });

        loginBTN=findViewById(R.id.signin);
        emailET=findViewById(R.id.email);
        passET=findViewById(R.id.password);


        firebaseAuth = FirebaseAuth.getInstance();

        loginBTN.setOnClickListener(v -> {
            logEmailPassUser(
                    emailET.getText().toString().trim(),
                    passET.getText().toString().trim()
            );
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void logEmailPassUser(
            String email, String pass
    ){
        if(!TextUtils.isEmpty(email)
            &&  !TextUtils.isEmpty(pass)){
            firebaseAuth.signInWithEmailAndPassword(
                    email,pass
            ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, TaskListActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        } else if (TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Incorrect Credentials" +
                    "Try Again or Connect to Internet", Toast.LENGTH_SHORT).show();
        }
    }

}
