package ru.strongit.realm_todo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.edition_container, new MyEditionFragment())
                    .add(R.id.list_container, new MyListFragment())
                    .commit();
        }


        Context ctx = getApplicationContext();

        mRealm = Realm.getInstance(ctx);

    }

}

//https://github.com/DroidsOnRoids/ExampleRealm-Android/blob/master/app/src/main/java/pl/droidsonroids/examplerealm/ui/MyMainActivity.java