/*
 * Copyright 2021 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news.services;

import cl.ucn.disc.dsn.mgonzalez.news.model.ArticleTemplate;
import cl.ucn.disc.dsn.mgonzalez.news.model.News;
import cl.ucn.disc.dsn.mgonzalez.news.newsapi.models.NewsTemplate;
import cl.ucn.disc.dsn.mgonzalez.news.newsapi.services.NewsAPIService;
import cl.ucn.disc.dsn.mgonzalez.news.utils.Validation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * The implementation of both News APIs (public https://newsapi.org and ours local) via Retrofit
 * library.
 *
 * @author Matthew Gonzalez Mansilla.
 */
public final class ContractsImplNewsAPIs implements  Contracts {

  /**
   * https://newsapi.org News API service.
   */
  private PublicNewsAPIService publicNewsAPIService;

  /**
   * Our local News API service.
   */
  private NewsAPIService localNewsAPIService;

  /**
   * The constructor.
   *
   * @param publicNewsAPIKey the API Key to access to the public https://newsapi.org News
   *                         API Service.
   * @param localNewsAPIBaseURL the IP and port Laravel local server is using to host our API News
   *                            service (Ex: http://192.168.1.84:8000/).
   */
  public ContractsImplNewsAPIs(String publicNewsAPIKey, String localNewsAPIBaseURL) {
    Validation.notNull(publicNewsAPIKey, "publicNewsAPIKey !!");
    Validation.notNull(localNewsAPIBaseURL, "localNewsAPIService !!");
    Validation.minSize(publicNewsAPIKey, 10, "publicNewsAPIKey !!");

    this.publicNewsAPIService = new PublicNewsAPIService(publicNewsAPIKey);
    this.localNewsAPIService = new NewsAPIService(localNewsAPIBaseURL);
  }

  /**
   * Converts an ArticleTemplate object into a News object.
   *
   * @param articleTemplate
   * @return a News object.
   */
  private static News articleTemplateToNews(final ArticleTemplate articleTemplate) {
    Validation.notNull(articleTemplate, "articleTemplate !!");

    // Fix the author null
    if (articleTemplate.getAuthor() == null || articleTemplate.getAuthor().length() == 0){
      articleTemplate.setAuthor("No author*");
    }

    // Fix more restrictions
    if (articleTemplate.getDescription() == null || articleTemplate.getDescription().length() == 0){
      articleTemplate.setDescription("No description*");
    }

    //
    if (articleTemplate.getContent() == null || articleTemplate.getContent().length() == 0){
      articleTemplate.setContent("No content*");
    }


    // The date
    ZonedDateTime publishedAt = ZonedDateTime.parse(articleTemplate.getPublishedAt())
        .withZoneSameInstant(ZoneId.of("-3"));

    // The News
    return new News(
        articleTemplate.getTitle(),
        articleTemplate.getSource().getName(),
        articleTemplate.getAuthor(),
        articleTemplate.getUrl(),
        articleTemplate.getUrlToImage(),
        articleTemplate.getDescription(),
        articleTemplate.getContent(),
        publishedAt
    );
  }

  /**
   * Converts a NewsTemplate object into a News object
   *
   * @param newsTemplate
   * @return a News object.
   */
  private static News newsTemplateToNews(final NewsTemplate newsTemplate) {
    ZonedDateTime publishedAt = ZonedDateTime.parse(newsTemplate.getPublished_at())
        .withZoneSameInstant(ZoneId.of("-3"));

    System.out.println(newsTemplate.getTitle());

    return new News(
        newsTemplate.getTitle(),
        newsTemplate.getSource(),
        newsTemplate.getAuthor(),
        newsTemplate.getUrl(),
        newsTemplate.getUrl_image(),
        newsTemplate.getDescription(),
        newsTemplate.getContent(),
        publishedAt
    );
  }

  /**
   * The Predicate to filter news.
   *
   * @param idExtractor
   * @param <T> news to filter.
   * @return true if the news already exist.
   */
  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> idExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(idExtractor.apply(t), Boolean.TRUE) == null;
  }

  /**
   * Get the list of News.
   *
   * @param size size of the list.
   * @return the List of News.
   */
  @Override
  public List<News> retrieveNews(Integer size) {

    if (size < 1) {
      throw new IllegalArgumentException("Error: size need to be > 0");
    }else {
      try {
        int trueSize = (size/2);

        // Get the list of Article
        List<ArticleTemplate> articleTemplateList = publicNewsAPIService.getTopHeadLines(
            trueSize,
            null,
            null,
            "technology",
            null
        );


        // Get the list of NewsTemplate
        List<NewsTemplate> newsTemplatesList = localNewsAPIService.getNews(
            (size % 2 == 0) ? trueSize : trueSize + 1,
            null,
            null
        );

        // The List of articles to List of news
        List<News> rawNews = new ArrayList<>();


        for (ArticleTemplate articleTemplate : articleTemplateList) {
          rawNews.add(articleTemplateToNews(articleTemplate));
        }


        for (NewsTemplate newsTemplate : newsTemplatesList){
          rawNews.add(newsTemplateToNews(newsTemplate));
        }

        // Return the news filtered and sorted by date
        return rawNews.stream()
            // Remove the duplicated (by keys)
            .filter(distinctByKey(News::getId))
            // Order by date
            .sorted((k1, k2) -> k2.getPublishedAt().compareTo(k1.getPublishedAt()))
            .collect(Collectors.toList());

      } catch (IOException e) {
        // Encapsulate!
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Save one News into the System.
   *
   * @param news to save.
   */
  @Override
  public void saveNews(News news) {

  }

}
