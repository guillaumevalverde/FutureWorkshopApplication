package com.gve.futureworkshopapplication.core.data;

import java.util.List;

import io.reactivex.Flowable;

public interface Repo<Value> {

    void fetch();

    Flowable<List<Value>> getStream();
}
