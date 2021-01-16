/*
 * Copyright 2021 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news.newsapi.models;

import java.util.List;

/**
 * Models the response of our News API.
 *
 * @author Matthew Gonzalez-Mansilla.
 */
public class NewsTemplateResponse {

  private int current_page;
  private List<NewsTemplate> data;
  private String first_page_url;
  private int from;
  private String next_page_url;
  private String path;
  private int per_page;
  private String prev_page_url;
  private int to;

  public int getCurrent_page() {
    return current_page;
  }

  public void setCurrent_page(int current_page) {
    this.current_page = current_page;
  }

  public List<NewsTemplate> getData() {
    return data;
  }

  public void setData(List<NewsTemplate> data) {
    this.data = data;
  }

  public String getFirst_page_url() {
    return first_page_url;
  }

  public void setFirst_page_url(String first_page_url) {
    this.first_page_url = first_page_url;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public String getNext_page_url() {
    return next_page_url;
  }

  public void setNext_page_url(String next_page_url) {
    this.next_page_url = next_page_url;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getPer_page() {
    return per_page;
  }

  public void setPer_page(int per_page) {
    this.per_page = per_page;
  }

  public String getPrev_page_url() {
    return prev_page_url;
  }

  public void setPrev_page_url(String prev_page_url) {
    this.prev_page_url = prev_page_url;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }

}
