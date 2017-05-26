package ru.strongit.realm_todo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.strongit.realm_todo.model.MyBook;

/**
 * Created by user on 26.05.17.
 */

public class MyEditionFragment extends Fragment implements View.OnClickListener {

    private Realm mRealm;

    Button mBtnAdd;
    Button mBtnRemove;
    EditText mEditTitle;

    @Override

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getInstance(getContext());

        mEditTitle = (EditText) getView().findViewById(R.id.edit_title);

        mBtnAdd = (Button) getView().findViewById(R.id.button_add);
        mBtnAdd.setOnClickListener(this);

        mBtnRemove = (Button) getView().findViewById(R.id.button_remove);
        mBtnRemove.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                mRealm.beginTransaction();

                MyBook book = mRealm.createObject(MyBook.class);
                book.setTitle(getTrimmedTitle());
                mRealm.commitTransaction();
                break;
            case R.id.button_remove:
                mRealm.beginTransaction();
                RealmResults<MyBook> books = mRealm.where(MyBook.class).equalTo("title", getTrimmedTitle()).findAll();
                if (!books.isEmpty()) {
                    for (int i = books.size() - 1; i >= 0; i--) {
                        books.get(i).removeFromRealm();
                    }
                }
                mRealm.commitTransaction();
                break;
        }
    }

    private String getTrimmedTitle() {


        return mEditTitle.getText().toString().trim();

    }
}
