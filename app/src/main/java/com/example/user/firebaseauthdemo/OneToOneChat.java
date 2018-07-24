package com.example.user.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OneToOneChat extends AppCompatActivity implements View.OnClickListener{

    private Button buttonA,buttonB,buttonC,buttonD,buttonE,goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_one_chat);

        buttonA=(Button) findViewById(R.id.buttonA);
        buttonB=(Button) findViewById(R.id.buttonB);
        buttonC=(Button) findViewById(R.id.buttonC);
        buttonD=(Button) findViewById(R.id.buttonD);
        buttonE=(Button) findViewById(R.id.buttonE);
        goBack=(Button) findViewById(R.id.goBack) ;

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        goBack.setOnClickListener(this);


    }

    public void onClick(View view) {
        if (view == buttonA) {
            String receiverUID= "yWHexk2E2JNnQ6qZM5u8eGCq4iZ2";
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            intent.putExtra("RECEIVERUID", receiverUID);
            startActivity(intent);
        }
        if (view == buttonB) {
            String receiverUID= "zWuQvFNMxoRv4OqYYrcZTSOL1i82";
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            intent.putExtra("RECEIVERUID", receiverUID);
            startActivity(intent);
        }
        if (view == buttonC) {
            String receiverUID= "HY30bYeLFUbtJCCBv0Qyj1KitTJ3";
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            intent.putExtra("RECEIVERUID", receiverUID);
            startActivity(intent);
        }
        if (view == buttonD) {
            String receiverUID= "YUdDpEfWfZRfh0TELkEjThaxL6J2";
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            intent.putExtra("RECEIVERUID", receiverUID);
            startActivity(intent);
        }
        if (view == buttonE) {
            String receiverUID= "OXpgZpjX8NOv9qmk4X934PqcmDF3";
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            intent.putExtra("RECEIVERUID", receiverUID);
            startActivity(intent);
        }
        if (view == goBack) {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

    }
}
