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
import cl.ucn.disc.dsn.mgonzalez.news.model.ArticleTemplateResponse;
import cl.ucn.disc.dsn.mgonzalez.news.network.PublicAPIService;
import cl.ucn.disc.dsn.mgonzalez.news.utils.Validation;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Public https://newsapi.org News API implementation.
 *
 * @author Matthew Gonzalez-Mansilla.
 */
public final class PublicNewsAPIService {

  /**
   * API key to access to the services.
   */
  private String apiKey;

  /**
   * The API service.
   */
  private PublicAPIService apiService;

  /**
   * A Retrofit instance to access to the public News API.
   */
  private Retrofit retrofit;

  /**
   * The Constructor.
   *
   * @param apiKey to access to public News API services.
   */
  public PublicNewsAPIService(String apiKey) {
    Validation.notNull(apiKey, "apiKey");
    this.apiKey = apiKey;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create()).build();

    apiService = retrofit.create(PublicAPIService.class);
  }


  /**
   * the getTopHeadLines converter.
   *
   * @param pageSize (optional, null to default) the number of articles to return.
   * @param page (optional, null to default) the page to return.
   * @param country (optional, null to default) the language of the articles.
   * @param category (optional, null to default) the category of the articles.
   * @param q (optional, null to default) the coincidence token. Allows to search any coincidence
   *          with the token in the title and/or in the content of the news.
   * @return an ArticleTemplate list.
   * @throws IOException in case of error.
   */
  public List<ArticleTemplate> getTopHeadLines(final Integer pageSize,
      final Integer page, final String country, final String category,
      final String q) throws IOException {
    // Create a query with the optional parameters
    Map<String, String> query = new HashMap<>();

    query.put("apiKey", apiKey);

    if (pageSize != null) {
      if (pageSize < 1) {
        throw new IllegalArgumentException("Error: pageSize need to be > 0");
      }else{
        query.put("pageSize", pageSize.toString());
      }
    }

    if (page != null) {
      if (page < 1) {
        throw new IllegalArgumentException("Error: page need to be > 0");
      }else{
        query.put("page", page.toString());
      }
    }

    if (country != null) {
      if(!q.isEmpty()) {
        query.put("country", country);
      }else {
        query.put("country", "us");
      }
    }

    if (category != null) {
      if(!category.isEmpty()) {
        query.put("category", category);
      }
    }

    if (q != null) {
      if(!q.isEmpty()) {
        query.put("q", q);
      }
    }

    // The response (synchronous!)
    Response<ArticleTemplateResponse> response = apiService.getTopHeadlines(query).execute();

    // All ok, return data
    if (response.isSuccessful()) {
      return response.body().getArticles();
    }

    return null;
    //throw new RuntimeException("Error: " + response.code() + " --> " + response.errorBody().string());
  }

}
