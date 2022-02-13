package com.massa844853.stockstracker.ui;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.massa844853.stockstracker.R;

public class LoginFragment extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText editTextEmail = view.findViewById(R.id.username);
        EditText editTextPassword = view.findViewById(R.id.password);
        Button buttonLoginEmail = view.findViewById(R.id.login_button);
        TextView buttonSkip = view.findViewById(R.id.skip_action);

        TextView createAccount = view.findViewById(R.id.textview_create_account);
        ProgressBar progressBar = view.findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.INVISIBLE);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Password is required.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            databaseReference.child("users").child(auth.getCurrentUser().getUid()).child("loginDateTime").setValue(System.currentTimeMillis());
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), "Error. " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}

