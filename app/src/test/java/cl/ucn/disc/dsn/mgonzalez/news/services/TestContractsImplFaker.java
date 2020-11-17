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
import com.github.javafaker.Faker;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * Test of ContractsImplFaker.
 *
 * @author  Matthew Gonzalez-Mansilla.
 */
public class TestContractsImplFaker {

  /**
   * The Logger
   */
  private static final Logger log = LoggerFactory.getLogger(TestContractsImplFaker.class);

  /**
   * The Test of Retrieve news.
   */
  @Test
  public void testRetrieveNews(){

    log.debug("Testing ..");

    // The concrete implementation
    Contracts contracts = new ContractsImplFaker();

    // Call the method
    List<News> news = contracts.retrieveNews(5);

    // .. the list cannot be null ..
    Assertions.assertNotNull(news, "List was null :(");

    // .. the list cannot be empty ..
    Assertions.assertFalse(news.isEmpty(), "Empty list? :(");

    // .. the list size = 5 ..
    Assertions.assertEquals(5, news.size(), "List size != 5 :(");

    // debug to log
    for (News n : news){
      log.debug("News : {}", n);
      System.out.println("News : " + n.getTitle());
    }

    // size = 0
    Assertions.assertEquals(0, contracts.retrieveNews(0).size(), "List size != 0");

    // size = 3
    Assertions.assertEquals(3, contracts.retrieveNews(3).size(), "List size != 3");

    // size = 10
    Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10, "List size != 10");

    log.debug("Done!");
  }

  /**
   *  Test the saveNews
   */
  @Test
  public void testSaveNews(){

    log.debug("Testing ..");

    //The faker to use
    final Faker faker = Faker.instance();

    // The concrete implementation
    Contracts contracts = new ContractsImplFaker();

    // Create a News
    News news = new News(Integer.toUnsignedLong(5), faker.book().title(),
        faker.name().username(), faker.name().fullName(), faker.internet().url(),
        faker.internet().avatar(), faker.harryPotter().quote(),
        faker.lorem().paragraph(3),
        ZonedDateTime.now(ZoneId.of("-3"))
    );

    // .. the news is null ..
    Assertions.assertNotNull(news, "News was null :(");

    // Call the method
    contracts.saveNews(news);

    // Retrieve News
    List<News> newsList = contracts.retrieveNews(6);

    // .. the list cannot be null ..
    Assertions.assertNotNull(newsList, "List was null :(");

    // .. the list cannot be empty ..
    Assertions.assertFalse(newsList.isEmpty(), "Empty list? :(");

    // .. the list size = 6 ..
    Assertions.assertEquals(6, newsList.size(), "List size != 6 :(");

    // debug to log
    for (News n : newsList){
      log.debug("News : {}", n);
      System.out.println("News : " + n.getTitle());
    }

    log.debug("Done!");
  }
}
