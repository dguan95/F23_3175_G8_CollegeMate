package com.example.collegemate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.gbuttons.GoogleSignInButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail;
    EditText inputPassword;
    Button btnSignIn;
    Button btnSignUp;
    DBHelper dbHelper;
    String userEmail="";

    GoogleSignInButton googleSignInButton;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        googleSignInButton = findViewById(R.id.btnSignInGoogle);

        dbHelper = new DBHelper(this);

        Log.d("Test", "Login Activity Opened");




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                User user = dbHelper.getUser(email, password);
                long userId = dbHelper.getUserIdByEmail(email);
                if (user == null) {
                    Toast.makeText(LoginActivity.this, "Login or Password is not correct", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent i = new Intent(LoginActivity.this, ProfilePage.class);
                    i.putExtra("user", user);
                    i.putExtra("userId", userId);
                    startActivity(i);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });




        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if (googleSignInAccount != null) {
            userEmail=googleSignInAccount.getEmail();
            Toast.makeText(this, "Google Email: " + userEmail, Toast.LENGTH_SHORT).show();
//            finish();
        }

        //open an activity and waiting for result, when result received the code inside onActivityResult is executed
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //data is intent  inside result
                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                        try {
                            //receiving google account from task
                            GoogleSignInAccount googleAccount = task.getResult(ApiException.class);
                            finish();

                            Intent intent;
                            if (googleSignInAccount != null) {
                                intent = new Intent(LoginActivity.this, ProfilePage.class);
                                //if account exists, retrieve user info
                                User user = dbHelper.getUser(googleSignInAccount.getEmail(), "google");
                                intent.putExtra("user", user);
                            } else {
                                User user = new User(googleAccount.getEmail(), "google");
                                //if account does not exist add user info
                                dbHelper.addUser(user);

                                intent = new Intent(LoginActivity.this, ProfileCreationActivity.class);
                                intent.putExtra("user", user);
                            }

                            startActivity(intent);
                        } catch (ApiException e) {
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call the intent created above
                Intent signInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(signInIntent);

            }
        });
    }
}