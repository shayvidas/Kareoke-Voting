package com.shayvidas.eurovisionvoting.ui.singers_select;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Superman Is Awsome on 04/02/2018.
 */

public class Singers_Select_Presenter {

    Singers_Select_Interface singersSelectInterface;
    Boolean isThereAGame = true;
    ArrayList<SingersModel> singersArray;


    public Singers_Select_Presenter(Singers_Select_Interface singersSelectInterface) {
        this.singersSelectInterface = singersSelectInterface;

    }

    //methods from activity
    ////////////////////////
    public void screenCreated() {

        if (checkForGame()) {
            initlizeArrayList();
            downloadSingers();

        }


    }


    //move to model
    public boolean checkForGame() {
        if (isThereAGame) {
            return true;
        } else {
            return false;
        }
    }




    public void initlizeArrayList() {
        singersArray = new ArrayList<SingersModel>();

    }

    public void downloadSingers() {
        final String TAG = "DownloadSingers";

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("game_one");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
