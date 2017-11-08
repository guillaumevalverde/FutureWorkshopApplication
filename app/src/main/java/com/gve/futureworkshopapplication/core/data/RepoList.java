package com.gve.futureworkshopapplication.core.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface RepoList<Value> {

    Completable fetch();

    Flowable<List<Value>> getStream();

}
