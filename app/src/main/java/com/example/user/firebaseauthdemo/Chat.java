package com.example.user.firebaseauthdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity implements View.OnClickListener {

    private Button buttonBack;
    private FirebaseListAdapter<ChatMessage> adapter;
    private String eventId;
    private String senderUID;
    private String recieverUID;
    private String privateChatroom;
    private String combinationA,combinationB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        senderUID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        recieverUID = getIntent().getStringExtra("RECEIVERUID");
        combinationA=senderUID+"_"+recieverUID;
        combinationB=recieverUID+"_"+senderUID;
        //privateChatroom=senderUID+"_"+recieverUID;

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        FirebaseDatabase.getInstance().getReference().child("OneToOneChat").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(combinationA)){
                    privateChatroom=senderUID+"_"+recieverUID;
                    displayChatMessages();
                }
                else if (dataSnapshot.hasChild(combinationB)){
                    privateChatroom=recieverUID+"_"+senderUID;
                    displayChatMessages();
                }
                else {
                    privateChatroom=senderUID+"_"+recieverUID;
                    displayChatMessages();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference().child("OneToOneChat").child(privateChatroom)
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                FirebaseAuth.getInstance().getCurrentUser().getEmail())
                        );
                        eventId = FirebaseDatabase.getInstance().getReference().child("OneToOneChat").child(privateChatroom).push()
                            .getKey();
                // Clear the input
                input.setText("");
                }

        });

    }

    private void displayChatMessages() {
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference().child("OneToOneChat").child(privateChatroom)) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonBack) {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
    }
}
