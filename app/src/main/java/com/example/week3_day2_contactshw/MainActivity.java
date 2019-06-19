package com.example.week3_day2_contactshw;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Permissions.IPermissionManager, ContactManager.IContractManager {
    Permissions permissionsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionsManager = new Permissions(this, this);
        permissionsManager.checkPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.checkResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onPermissionResult(boolean isGranted) {
        Log.d("TAG", "onPermissionResult: YEP" );
        if(isGranted) {
            getContacts();
        } else {
            Toast.makeText(this, "Can not process", Toast.LENGTH_SHORT).show();
        }
    }

    public void getContacts() {
        ContactManager contactsManager = new ContactManager();
        contactsManager.getContacts();
    }

    @Override
    public void onContactsReceived(List<Contact> contactsList) {
        for(Contact contact : contactsList) {
            Log.d("TAG", "onContactsReceived: " + contact.toString());
        }
    }
}
