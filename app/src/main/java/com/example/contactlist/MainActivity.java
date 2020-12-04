package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.contactlist.Adapters.ContactAdapter;
import com.example.contactlist.ModelClass.ContactModelClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView contact;
    RecyclerView recyclerView;

    List<ContactModelClass> contactList=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter contactAdapter=new ContactAdapter(this,contactList);
        recyclerView.setAdapter(contactAdapter);

        initField();
        addDataInList();
    }

    private void addDataInList() {
        contactList.add(new ContactModelClass("Ali",123456789));
        contactList.add(new ContactModelClass("Ahmed",012345667));
        contactList.add(new ContactModelClass("hamza",123456789));
        contactList.add(new ContactModelClass("Ali",12344567));
        contactList.add(new ContactModelClass("hamza",123456789));
        contactList.add(new ContactModelClass("Ahmed",567239203));
        contactList.add(new ContactModelClass("akbar",123456789));
        contactList.add(new ContactModelClass("bilal",426796159));
        contactList.add(new ContactModelClass("imran",123456789));
        contactList.add(new ContactModelClass("Ali",432179654));

    }

    private void initField() {

        contact=findViewById(R.id.contact);
        recyclerView=findViewById(R.id.recycler_view);

    }
}