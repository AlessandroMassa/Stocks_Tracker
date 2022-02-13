package com.massa844853.stockstracker.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.adapter.ChatAdapter;
import com.massa844853.stockstracker.models.Message;
import com.massa844853.stockstracker.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatFragment extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private User user;
    private Map<String, Message> messages;
    private List<Message> messagesList;
    private List<Message> newMessages;
    private boolean updateRecyclerView;
    private ChatAdapter chatAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        messagesList = new ArrayList<>();
        user = ((MainActivity)getActivity()).getUser();
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("chat");
        messages = new HashMap<>();
        updateRecyclerView = false;

        EditText editTextMessage = view.findViewById(R.id.inputMessage);
        ImageView buttonSend = view.findViewById(R.id.buttonSend);

        RecyclerView recyclerView = view.findViewById(R.id.chatRecyclerView);
        chatAdapter = new ChatAdapter(messagesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatAdapter);

        if(auth.getCurrentUser() != null)
        {
            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textMessage = editTextMessage.getText().toString();
                    editTextMessage.setText("");

                    if(!TextUtils.isEmpty(textMessage))
                    {
                        Message message = new Message(user.getUsername(), System.currentTimeMillis(), textMessage);

                        databaseReference.getParent().child("chat").child(auth.getCurrentUser().getUid() + "-" + user.getNextMessage()).setValue(message);
                        user.setNextMessage(user.getNextMessage() + 1);

                        databaseReference.getParent().child("users").child(auth.getCurrentUser().getUid()).setValue(user);

                    }
                }
            });

            databaseReference = FirebaseDatabase.getInstance().getReference().child("chat");

            ValueEventListener chatListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<Map<String, Message>> typeIndicator = new GenericTypeIndicator<Map<String, Message>>() {};
                    Map<String, Message> messagesSnap = dataSnapshot.getValue(typeIndicator);
                    newMessages = new ArrayList<>();
                    if(messagesSnap != null) {
                        for (Map.Entry<String, Message> entry : messagesSnap.entrySet()) {
                            if (messages.get(entry.getKey()) == null && entry.getValue().getSendDate() > user.getLoginDateTime()) {
                                if (entry.getValue().getUsername().equals(user.getUsername())) {
                                    entry.getValue().setTypeMessage(1);
                                } else {
                                    entry.getValue().setTypeMessage(0);
                                }
                                messages.put(entry.getKey(), entry.getValue());
                                newMessages.add(entry.getValue());
                                updateRecyclerView = true;
                            }
                        }
                        if (updateRecyclerView) {
                            messagesList.addAll(newMessages);
                            chatAdapter.notifyDataSetChanged();
                            updateRecyclerView = false;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Error. "+ databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            };
            databaseReference.addValueEventListener(chatListener);
        }


        return view;
    }
}