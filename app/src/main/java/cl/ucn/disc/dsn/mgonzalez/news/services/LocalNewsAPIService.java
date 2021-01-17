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

import cl.ucn.disc.dsn.mgonzalez.news.model.NewsTemplate;
import cl.ucn.disc.dsn.mgonzalez.news.model.NewsTemplateResponse;
import cl.ucn.disc.dsn.mgonzalez.news.network.LocalAPIService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The implementation of our News API service.
 *
 * @author Matthew Gonzalez-Mansilla.
 */
public final class LocalNewsAPIService {

  /**
   * A Retrofit instance to access to our News API.
   */
  private Retrofit retrofit;

  /**
   * The API Service.
   */
  private LocalAPIService apiService;

  /**
   * The constructor.
   *
   * @param baseURL of our News API.
   */
  public LocalNewsAPIService(String baseURL) {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create()).build();

    apiService = retrofit.create(LocalAPIService.class);
  }

  /**
   * The getNews adaptor.
   *
   * @param pageSize (optional, null to default) the number of news to return.
   * @param page (optional, null to default) the page to return.
   * @param q (optional, null to default) the coincidence token. Allows to search any coincidence with the token in the title
   *          and/or in the content of the news.
   * @return a NewsTemplate list.
   * @throws IOException in case of error.
   */
  public List<NewsTemplate> getNews(final Integer pageSize, final Integer page, final String q) throws IOException {
    // Create a query with the optional parameters
    Map<String, String> query = new HashMap<>();

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

    if (q != null) {
      if(!q.isEmpty()) {
        query.put("q", q);
      }
    }

    Response<NewsTemplateResponse> response = apiService.getNews(query).execute();

    if (response.isSuccessful()) {
      return response.body().getData();
    }

    //throw new RuntimeException("Error: " + response.code() + " --> " + response.errorBody().string());

    return null;
  }
}
