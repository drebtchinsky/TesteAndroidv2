package com.drebtchinsky.testeandroidv2.asynctask;

import android.os.AsyncTask;

import java.util.List;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final ExecuteListener<T> executeListener;
    private final FinishedListener<T> finishedListener;

    public BaseAsyncTask(ExecuteListener<T> executeListener,
                         FinishedListener<T> finishedListener) {
        this.executeListener = executeListener;
        this.finishedListener = finishedListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return executeListener.whenExecute();
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        finishedListener.whenFinished(result);
    }

    public interface ExecuteListener<T> {
        T whenExecute();
    }

    public interface FinishedListener<T> {
        void whenFinished(T result);
    }

}