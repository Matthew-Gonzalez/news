/*
 * Copyright 2020 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news.services;

import cl.ucn.disc.dsn.mgonzalez.news.model.News;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing the Contracts with NewsApi service.
 *
 * @author Matthew Gonzalez-Mansilla
 */
public class TestContractsImplNewsApi {

  /**
   * The test of retrieve news.
   */
  @Test
  public void testRetrieveNews(){

    // The Contracts
    Contracts contracts = new ContractsImplNewsApi("05f5d086cbc647e6bd2a902a8758af3b");

    // The List of news
    int size = 40;
    List<News> news = contracts.retrieveNews(size);

    // Validations
    Assertions.assertNotNull(news, "List null !!");
    Assertions.assertEquals(size, news.size(), "Wrong size !!");

    if (news != null && news.size() > 0){
      for (News newsItem : news){
        System.out.println("Title: " + newsItem.getTitle() + '\n');
      }
    }
  }
}
