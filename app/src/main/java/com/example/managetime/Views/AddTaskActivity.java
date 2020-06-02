package com.example.managetime.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managetime.R;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskTitle;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        init();
    }

    private void init() {
        editTextTaskTitle = (EditText) findViewById(R.id.taskTitle);

        editTextTaskTitle.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
/*        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.add();
            }
        });*/

/*        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        userAdapter = new UserAdapter();

        RecyclerView userList = (RecyclerView) findViewById(R.id.list);
        userList.setLayoutManager(layoutManager);
        userList.setAdapter(userAdapter);


        DbHelper dbHelper = new DbHelper(this);
        UsersModel usersModel = new UsersModel(dbHelper);
        presenter = new UsersPresenter(usersModel);
        presenter.attachView(this);
        presenter.viewIsReady();*/

    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
