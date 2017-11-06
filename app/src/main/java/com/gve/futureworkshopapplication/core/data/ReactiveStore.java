package com.gve.futureworkshopapplication.core.data;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.util.List;

import io.reactivex.Flowable;

public interface ReactiveStore<Value> {

    void storeAll(@NonNull final List<Value> modelList);

    Flowable<Pair<List<Value>, Long>> getAll();
}
