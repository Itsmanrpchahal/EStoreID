package com.estoreid.estoreid.views.adapter;

public interface AsyncTask<TData> {
    void success(TData data);
    void error(String error);
}
