package com.example.managetime.Model.dao;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.example.managetime.Model.DBHelper;

import java.util.concurrent.TimeUnit;

public class DBManager {

    private final DBHelper dbHelper;

    public DBManager(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void addUser(ContentValues contentValues, CompleteCallback callback) {
        AddUserTask addUserTask = new AddUserTask(callback);
        addUserTask.execute(contentValues);
    }

    interface CompleteCallback {
        void onComplete();
    }

    class AddUserTask extends AsyncTask<ContentValues, Void, Void> {

        private final CompleteCallback callback;

        AddUserTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... params) {
            ContentValues cvUser = params[0];
            dbHelper.getWritableDatabase().insert(dbHelper.USERS_TABLE_NAME, null, cvUser);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }
}
