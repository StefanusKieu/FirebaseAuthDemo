package com.example.user.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUserEmail;
    private Button buttonLogout;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;
    private EditText editTextName,editTextAddress;
    private Button buttonSave;
    private Button gotoChat,buttonOTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth= FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference= FirebaseDatabase.getInstance().getReference();
        editTextAddress=(EditText) findViewById(R.id.editTextAddress);
        editTextName=(EditText)findViewById(R.id.editTextName);
        buttonSave=(Button) findViewById(R.id.buttonAddPeople);
        gotoChat=(Button) findViewById(R.id.gotoChat);
        buttonOTO=(Button) findViewById(R.id.buttonOTO);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        gotoChat.setOnClickListener(this);
        buttonOTO.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String add= editTextAddress.getText().toString().trim();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uID=user.getUid();
        UserInformation userInformation = new UserInformation(name,add,uID);
        databaseReference.child(uID).setValue(userInformation);
        //databaseReference.setValue(userInformation);
        Toast.makeText(this,"Information Saved.",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view==buttonSave){
            saveUserInformation();
        }
        if (view==gotoChat){
            //finish();
            //startActivity(new Intent(getApplicationContext(),Chat.class));
        }
        if (view==buttonOTO){
            finish();
            startActivity(new Intent(getApplicationContext(),OneToOneChat.class));
        }
    }
    public void gotochatroom(){
        startActivity(new Intent(getApplicationContext(),Chat.class));
        startActivity(new Intent(this, Chat.class));
    }

}
