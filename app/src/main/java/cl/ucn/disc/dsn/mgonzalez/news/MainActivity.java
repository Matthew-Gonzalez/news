/*
 * Copyright 2020 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cl.ucn.disc.dsn.mgonzalez.news.model.News;
import cl.ucn.disc.dsn.mgonzalez.news.services.ContractsImplNewsApi;
import java.util.List;

/**
 * The main class.
 *
 * @author Matthew Gonzalez-Mansilla
 */
public class MainActivity extends AppCompatActivity {

  /**
   * The ListView.
   */
  protected ListView listView;

  /**
   * OnCreate.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.listView = findViewById(R.id.am_lv_news);

    // Get the news in the background thread
    AsyncTask.execute(() -> {
      // Using the contracts to get the news
      ContractsImplNewsApi contracts = new ContractsImplNewsApi("05f5d086cbc647e6bd2a902a8758af3b");

      // Get the news from internet
      List<News> newsList = contracts.retrieveNews(30);

      // Build a simple adapter to show the list of news as String
      ArrayAdapter<String> adapter = new ArrayAdapter(
          this,
          android.R.layout.simple_expandable_list_item_1,
          newsList
      );

      // Set the adapter
      runOnUiThread(() -> {
        this.listView.setAdapter(adapter);
      });
    });
  }
}