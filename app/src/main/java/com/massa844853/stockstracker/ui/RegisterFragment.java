package com.massa844853.stockstracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.models.User;

public class RegisterFragment extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference database;
    public RegisterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText editTextEmail = view.findViewById(R.id.email);
        EditText editTextPassword = view.findViewById(R.id.password);
        EditText editTextUsername = view.findViewById(R.id.username);

        Button buttonRegisterEmail = view.findViewById(R.id.register_button);
        ProgressBar progressBar = view.findViewById(R.id.progressBarRegister);
        progressBar.setVisibility(View.INVISIBLE);
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance().getReference();

        buttonRegisterEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String username = editTextUsername.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    editTextEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    editTextPassword.setError("Password is required.");
                    return;
                }
                if(TextUtils.isEmpty(username))
                {
                    editTextUsername.setError("Username is required.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            String uId = auth.getCurrentUser().getUid();
                            User newUser = new User(System.currentTimeMillis(), 0, username);
                            database.child("users").child(uId).setValue(newUser);

                            startActivity(new Intent(getContext(),MainActivity.class));
                            getActivity().finish();

                        }
                        else
                        {
                            Toast.makeText(getContext(), "Error. "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });
        return view;
    }
}