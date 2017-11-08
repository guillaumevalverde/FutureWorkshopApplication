package com.gve.futureworkshopapplication.articleDetail;

import android.arch.persistence.room.EmptyResultSetException;

import com.gve.futureworkshopapplication.articledetail.data.ArticleDetailRepo;
import com.gve.futureworkshopapplication.articledetail.data.ArticleStore;
import com.gve.futureworkshopapplication.test_common.BaseTest;
import com.gve.futureworkshopapplication.articlelist.data.Article;
import com.gve.futureworkshopapplication.articlelist.data.ArticleDetailRaw;
import com.gve.futureworkshopapplication.articlelist.data.RetrofitApiService;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

/**
 * Created by gve on 08/11/2017.
 */

public class ArticleDetailRepoTest extends BaseTest {

    private Article article = new Article(100,"title1", "imageUrl", "source", "summary", "content", "date", false);
    private ArticleDetailRaw articleRaw = ArticleDetailRaw.builder().id(100).title("title1").imageUrl("imageUrl").source("source").content("content").date("date").build();

    @Mock
    RetrofitApiService retrofitApiService;

    @Mock
    ArticleStore articleStore;

    @Test
    public void detailRepoArticleAllreadyInStoreTest() {
        ArticleDetailRepo articleDetailRepo = new ArticleDetailRepo(retrofitApiService, articleStore);
        Mockito.when(articleStore.getArticleSingle(100)).thenReturn(Single.just(article));

        TestObserver testObserver = articleDetailRepo.fetchArticle(100).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void detailRepoArticleNotInStoreTest() {
        ArticleDetailRepo articleDetailRepo = new ArticleDetailRepo(retrofitApiService, articleStore);
        Mockito.when(articleStore.getArticleSingle(100)).thenReturn(Single.error(new EmptyResultSetException("no article")));
        Mockito.when(retrofitApiService.getArticle("" + 100)).thenReturn(Single.just(articleRaw));
    TestObserver testObserver = articleDetailRepo.fetchArticle(100).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }
}
