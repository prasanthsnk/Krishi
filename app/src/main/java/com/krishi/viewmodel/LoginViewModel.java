package com.krishi.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.krishi.App;
import com.krishi.model.Users;
import com.krishi.utils.Constants;
import com.krishi.view.activity.HomeActivity;
import com.krishi.view.activity.RegisterActivity;

/**
 * Created by Prasanth on 14-03-2020.
 * Honeywell
 */
public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<String> mobileNO = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public void setupModel(AppCompatActivity context) {
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        FirebaseMessaging.getInstance().subscribeToTopic("Krishi")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "success";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d("TAG", msg);
                    }
                });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onUpdateViewModel() {

    }

    public void onClickLogin(View view) {
        showDialogPopup("Loading...");
        DatabaseReference myRef = database.getReference("Users/" + mobileNO.getValue());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dismissDialogPopup();
                if (dataSnapshot.exists()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    if (users.getPassword().equals(password.getValue())) {
                        database.getReference().child("Users").child(mobileNO.getValue()).child("token").setValue(App.readShared(Constants.ACCESS_TOKEN));
                        App.saveShared(Constants.MOBILE_NUMBER, mobileNO.getValue());
                        redirect();
                    } else {
                        showToast("Invalid credentials.");
                    }
                } else {
                    showToast("Invalid credentials.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dismissDialogPopup();
            }
        });
    }

    public void onClickRegister(View view) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    private void redirect() {
        context.startActivity(new Intent(context, HomeActivity.class));
        context.finish();
    }
}
