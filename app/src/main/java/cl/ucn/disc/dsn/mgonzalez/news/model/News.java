/*
 * Copyright 2020 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news.model;

import org.threeten.bp.ZonedDateTime;

/**
 * The Domain Model: News.
 *
 * @author Matthew Gonzalez-Mansilla
 */
public final class News {

  /**
   * Unique id.
   */
  private Long id;

  /**
   * The title.
   * Restrictions: not null, size > 2.
   */
  private String title;

  /**
   * The source.
   */
  private String source;

  /**
   * The author.
   */
  private String author;

  /**
   * The URL.
   */
  private String url;

  /**
   * The image url.
   */
  private String urlImage;

  /**
   * The description.
   */
  private String description;

  /**
   * The content.
   */
  private String content;

  /**
   * The publish date.
   */
  private ZonedDateTime publishedAt;

  /**
   * The constructor.
   *
   * @param id
   * @param title
   * @param source
   * @param author
   * @param url
   * @param urlImage
   * @param description
   * @param content
   * @param publishedAt
   */
  public News(Long id, String title, String source, String author, String url,
      String urlImage, String description, String content, ZonedDateTime publishedAt) {
    this.id = id;
    this.title = title;
    this.source = source;
    this.author = author;
    this.url = url;
    this.urlImage = urlImage;
    this.description = description;
    this.content = content;
    this.publishedAt = publishedAt;
  }

  /**
   * @return the id.
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the source.
   */
  public String getSource() {
    return source;
  }

  /**
   * @return the author.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @return the URL.
   */
  public String getUrl() {
    return url;
  }

  /**
   * @return the image url.
   */
  public String getUrlImage() {
    return urlImage;
  }

  /**
   * @return the description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the content.
   */
  public String getContent() {
    return content;
  }

  /**
   * @return the publish date.
   */
  public ZonedDateTime getPublishedAt() {
    return publishedAt;
  }
}
