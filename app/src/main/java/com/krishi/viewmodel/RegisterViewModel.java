package com.krishi.viewmodel;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.krishi.App;
import com.krishi.model.Users;
import com.krishi.utils.Constants;

/**
 * Created by Prasanth on 14-03-2020.
 * Honeywell
 */
public class RegisterViewModel extends BaseViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> mobileNO = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();

    public void setupModel(AppCompatActivity context) {
        setUp(context);
        init();
    }

    @Override
    protected void init() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onUpdateViewModel() {

    }

    public void onClickRegister(View view) {
        showDialogPopup("Loading...");
        DatabaseReference myRef = database.getReference("Users/" + mobileNO.getValue());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dismissDialogPopup();
                if (dataSnapshot.exists()) {
                    showToast("User already exists.");
                } else if (!dataSnapshot.exists()) {
                    Users user = new Users();
                    user.setName(name.getValue());
                    user.setPassword(password.getValue());
                    user.setToken(App.readShared(Constants.ACCESS_TOKEN));
                    database.getReference().child("Users").child(mobileNO.getValue()).setValue(user);
                    context.finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dismissDialogPopup();
            }
        });
    }
}
