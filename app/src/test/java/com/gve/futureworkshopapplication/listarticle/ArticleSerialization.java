package com.gve.futureworkshopapplication.listarticle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gve.futureworkshopapplication.core.app.DataAdapterFactory;
import com.gve.futureworkshopapplication.userarticle.data.ArticleDetailRaw;
import com.gve.futureworkshopapplication.userarticle.data.ArticleRaw;
import com.gve.futureworkshopapplication.userarticle.data.ListArticle;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by gve on 07/11/2017.
 */

public class ArticleSerialization {

    private static final String ARTICLE_1_JSON = "{\n" +
            "      \"id\": 100,\n" +
            "      \"title\": \"'iPhone 8' to Come in Four Colors Including New 'Mirror-Like' Option\",\n" +
            "      \"icon_url\": \"https://cdn.macrumors.com/article-new/2017/07/DEOsfxlXcAA7RYl.jpg-large-800x600.jpeg\",\n" +
            "      \"summary\": \"Apple could make its upcoming OLED iPhone available in four different shades, including a new mirror-like reflective version not seen before in previous models. That's the latest claim from mobile leaker Benjamin Geskin, who shared an example image via Twitter over the weekend showing what the new color option could resemble.\",\n" +
            "      \"date\": \"11/12/2016\",\n" +
            "      \"content\": \"https://s3.amazonaws.com/future-workshops/100.json\"\n" +
            "    }";

    private static final String summary =  "Apple could make its upcoming OLED iPhone available in four different shades, including a new mirror-like reflective version not seen before in previous models. That's the latest claim from mobile leaker Benjamin Geskin, who shared an example image via Twitter over the weekend showing what the new color option could resemble.";
    private static final String date = "11/12/2016";
    private static final String content = "https://s3.amazonaws.com/future-workshops/100.json";


    private static final String ARTICLE_DETAIL_100 = "{\n" +
            "  \"id\":100,\n" +
            "  \"title\":\"'iPhone 8' to Come in Four Colors Including New 'Mirror-Like' Option\",\n" +
            "  \"image_url\":\"https://cdn.macrumors.com/article-new/2017/07/DEOsfxlXcAA7RYl.jpg-large-800x600.jpeg\",\n" +
            "  \"source\":\"macrumors\",\n" +
            "  \"content\":\"Apple could make its upcoming OLED iPhone available in four different shades, including a new mirror-like reflective version not seen before in previous models. That's the latest claim from mobile leaker Benjamin Geskin, who shared an example image via Twitter over the weekend showing what the new color option could resemble.\",\n" +
            "  \"date\":\"11/12/2016\"\n" +
            "}";
    private static final String title_100 = "'iPhone 8' to Come in Four Colors Including New 'Mirror-Like' Option";
    private static final String image_url_100 = "https://cdn.macrumors.com/article-new/2017/07/DEOsfxlXcAA7RYl.jpg-large-800x600.jpeg";
    private static final String source_100 = "macrumors";
    private static final String content_100 = "Apple could make its upcoming OLED iPhone available in four different shades, including a new mirror-like reflective version not seen before in previous models. That's the latest claim from mobile leaker Benjamin Geskin, who shared an example image via Twitter over the weekend showing what the new color option could resemble.";
    private static final String date_100 = "11/12/2016";

    private static final String LIST_ARTICLE = "{\n" +
            "  \"articles\": [\n" +
            "    {\n" +
            "      \"id\": 100,\n" +
            "      \"title\": \"'iPhone 8' to Come in Four Colors Including New 'Mirror-Like' Option\",\n" +
            "      \"icon_url\": \"https://cdn.macrumors.com/article-new/2017/07/DEOsfxlXcAA7RYl.jpg-large-800x600.jpeg\",\n" +
            "      \"summary\": \"Apple could make its upcoming OLED iPhone available in four different shades, including a new mirror-like reflective version not seen before in previous models. That's the latest claim from mobile leaker Benjamin Geskin, who shared an example image via Twitter over the weekend showing what the new color option could resemble.\",\n" +
            "      \"date\": \"11/12/2016\",\n" +
            "      \"content\": \"https://s3.amazonaws.com/future-workshops/100.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 107,\n" +
            "      \"title\": \"10 Years Ago Today, the Original iPhone Officially Launched\",\n" +
            "      \"icon_url\": \"https://cdn.macrumors.com/article-new/2017/01/original-iphone.jpg\",\n" +
            "      \"summary\": \"Exactly 10 years ago today, on June 29, 2007, the original iPhone went on sale, six months after Steve Jobs stood onstage at Macworld Expo 2007 in San Francisco and told the world Apple was reinventing the phone, revolutionizing an entire industry like it had done with the Macintosh in 1984 and the iPod in 2001. \",\n" +
            "      \"date\": \"29/06/2017\",\n" +
            "      \"content\": \"https://s3.amazonaws.com/future-workshops/107.json\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private Gson gson;

    @Before
    //TODO Inject Gson Module
    // TODO resolve dagger2 bug where test component are not generated
    public void setUp() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(DataAdapterFactory.create());
        gson = builder.create();
    }

    @Test
    public void deserializationArticleJsonTest() {
        ArticleRaw articleRaw = gson.fromJson(ARTICLE_1_JSON, ArticleRaw.class);

        assertEquals(100, articleRaw.id());
        assertEquals("'iPhone 8' to Come in Four Colors Including New 'Mirror-Like' Option", articleRaw.title());
        assertEquals("https://cdn.macrumors.com/article-new/2017/07/DEOsfxlXcAA7RYl.jpg-large-800x600.jpeg", articleRaw.iconUrl());
        assertEquals(summary, articleRaw.summary());
        assertEquals(date, articleRaw.date());
        assertEquals(content, articleRaw.content());
    }

    @Test
    public void deserializationArticleDetailJsonTest() {
        ArticleDetailRaw article = gson.fromJson(ARTICLE_DETAIL_100, ArticleDetailRaw.class);

        assertEquals(100, article.id());
        assertEquals(title_100, article.title());
        assertEquals(image_url_100, article.imageUrl());
        assertEquals(source_100, article.source());
        assertEquals(date_100, article.date());
        assertEquals(content_100, article.content());
    }

    @Test
    public void deserializeListArticle() {
        List<ArticleRaw> articleList = gson.fromJson(LIST_ARTICLE, ListArticle.class).getArticleList();

        assertEquals(2, articleList.size());
    }
}
