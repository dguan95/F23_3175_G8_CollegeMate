package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText inputEmail;
    EditText inputPassword;
    EditText inputPasswordConfirm;
    DBHelper dbHelper;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputEmail = findViewById(R.id.ipnutEmail2);
        inputPassword = findViewById(R.id.inputPassword2);
        inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        inputPasswordConfirm = findViewById(R.id.confirmPassword2);
        inputPasswordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btnCreate = findViewById(R.id.btnCreate);

        dbHelper = new DBHelper(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                String passwordConfirm = inputPasswordConfirm.getText().toString();

                User user = new User(email, password);

                try {
                    Pattern regexPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = regexPattern.matcher(email);



                    if (!(matcher.matches())) {
                        Toast.makeText(SignUpActivity.this, "Email invalid", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(password.length()< 8 )
                    {
                        Toast.makeText(SignUpActivity.this, "Password must contain at least 8 characters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(!(password.equals(passwordConfirm)))
                    {
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (dbHelper.getUser(email, password) != null){
                        Toast.makeText(SignUpActivity.this, "This user already exists.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    dbHelper.addUser(user);

                    Intent i = new Intent(SignUpActivity.this,ProfileCreationActivity.class);
                    i.putExtra("user", user);

                    Toast.makeText(SignUpActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(i);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}