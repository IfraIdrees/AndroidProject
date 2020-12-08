package com.example.contactlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.contactlist.Adapters.ContactAdapter;
import com.example.contactlist.ModelClass.ContactModelClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView contact;
    RecyclerView recyclerView;

    List<ContactModelClass> contactList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initField();




        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getContact();
        }

    }

    private void initField() {

        contact = findViewById(R.id.contact);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter contactAdapter = new ContactAdapter(this, getContact());
        recyclerView.setAdapter(contactAdapter);
    }

    private List<ContactModelClass> getContact() {

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                , null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contactList.add(new ContactModelClass(name,mobile));
        }
        return contactList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){

            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getContact();
            }

        }
    }
}