package com.example.acorpas.androidchatmvp.contactlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.acorpas.androidchatmvp.AndroidChatApplication;
import com.example.acorpas.androidchatmvp.R;
import com.example.acorpas.androidchatmvp.addcontact.ui.AddContactFragment;
import com.example.acorpas.androidchatmvp.contactlist.ContactListPresenter;
import com.example.acorpas.androidchatmvp.contactlist.ContactListPresenterImpl;
import com.example.acorpas.androidchatmvp.contactlist.ui.adapters.ContactListAdapter;
import com.example.acorpas.androidchatmvp.contactlist.ui.adapters.OnItemClickListener;
import com.example.acorpas.androidchatmvp.entities.User;
import com.example.acorpas.androidchatmvp.lib.GlideImageLoader;
import com.example.acorpas.androidchatmvp.lib.ImageLoader;
import com.example.acorpas.androidchatmvp.login.ui.LoginActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private ContactListPresenter contactListPresenter;
    private ContactListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        contactListPresenter = new ContactListPresenterImpl(this);
        contactListPresenter.onCreate();

        toolbar.setSubtitle(contactListPresenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);


        setupAdapter();
        setupRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            contactListPresenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        AndroidChatApplication app = (AndroidChatApplication)getApplication();
        ImageLoader imageLoader = new GlideImageLoader(this);
        adapter = new ContactListAdapter(new ArrayList<User>(), imageLoader, this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactListPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        contactListPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        contactListPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    public void addContact(){
        new AddContactFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_message_title));
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
       /* Intent i = new Intent(this, ChatActivity.class);
        i.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        i.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(i);*/
    }

    @Override
    public void onItemLongClick(User user) {
        contactListPresenter.removeContact(user.getEmail());
    }
}
