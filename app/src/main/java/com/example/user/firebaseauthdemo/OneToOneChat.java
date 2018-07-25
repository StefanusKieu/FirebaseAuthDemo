package com.example.user.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class OneToOneChat extends AppCompatActivity implements View.OnClickListener{

    private Button goBack;
    private FirebaseListAdapter<UserInformation> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_one_chat);

        goBack=(Button) findViewById(R.id.goBack) ;


        goBack.setOnClickListener(this);

        displayListOfUsers();
    }

    private void displayListOfUsers() {
        ListView listOfUsers = (ListView)findViewById(R.id.listOfUsers);

        adapter = new FirebaseListAdapter<UserInformation>(this, UserInformation.class,
                R.layout.userlist, FirebaseDatabase.getInstance().getReference().child("AllUSerInfo")) {
            @Override
            protected void populateView(View v, UserInformation model, int position) {
                // Get references to the views of message.xml
                Button usernameButton = (Button)v.findViewById(R.id.list_email);
                final TextView userUID = (TextView)v.findViewById(R.id.list_uid);

                // Set their text
                usernameButton.setText(model.getName());
                userUID.setText(model.getuID());
                usernameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String receiverUID= userUID.getText().toString().trim();
                        Intent intent = new Intent(getApplicationContext(), Chat.class);
                        intent.putExtra("RECEIVERUID", receiverUID);
                        startActivity(intent);
                    }
                });
            }
        };

        listOfUsers.setAdapter(adapter);
    }


    public void onClick(View view) {
        //if (view == buttonA) {String receiverUID= "yWHexk2E2JNnQ6qZM5u8eGCq4iZ2";Intent intent = new Intent(getApplicationContext(), Chat.class);intent.putExtra("RECEIVERUID", receiverUID);startActivity(intent);}
        if (view == goBack) {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

    }
}
