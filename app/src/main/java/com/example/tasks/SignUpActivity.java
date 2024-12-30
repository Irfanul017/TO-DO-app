package com.example.tasks;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    EditText password_create, username_create, email_create;
    Button createBTN;
    TextView redirect;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        createBTN=findViewById(R.id.acc_sign_up);
        password_create=findViewById(R.id.password_create);
        username_create=findViewById(R.id.username_create_ET);
        email_create=findViewById(R.id.email_create);
        redirect=findViewById(R.id.redirectmain);
        redirect.setOnClickListener(v->{
            Intent i = new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        });

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                if(currentUser != null){

                }else{

                }
            }
        };

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email_create.getText().toString())
                    &&  !TextUtils.isEmpty(username_create.getText().toString())
                        &&      !TextUtils.isEmpty(password_create.getText().toString())){

                    String email = email_create.getText().toString().trim();
                    String pass = password_create.getText().toString().trim();
                    String username = username_create.getText().toString().trim();

                    CreateUserEmailAccount(email,pass,username);

                }else{
                    Toast.makeText(SignUpActivity.this, "No Empty Fields Are Allowed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void CreateUserEmailAccount(
        String email,
        String pass,
        String username
    ){
        if(!TextUtils.isEmpty(email)
           &&     !TextUtils.isEmpty(pass)
                    &&  !TextUtils.isEmpty(username)){
            firebaseAuth.createUserWithEmailAndPassword(
                    email,pass
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Account is Created Successfully", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(SignUpActivity.this, "Authentication Failed"+"\n Try Again or Connect to Internet", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }

}
