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
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsn.mgonzalez.news.model.News;
import cl.ucn.disc.dsn.mgonzalez.news.services.ContractsImplNewsApi;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ModelAdapter;
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
   * @param savedInstanceState used to reload the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // The FasterAdapter
    ModelAdapter<News, NewsItem> newsAdapter = new ModelAdapter<>(NewsItem::new);
    FastAdapter<NewsItem> fastAdapter = FastAdapter.with(newsAdapter);
    fastAdapter.withSelectable(false);

    // The Recycler view
    RecyclerView recyclerView = findViewById(R.id.am_rv_news);
    recyclerView.setAdapter(fastAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new DividerItemDecoration(this
        , DividerItemDecoration.VERTICAL));

    // Get the news in the background thread
    AsyncTask.execute(() -> {
      // Using the contracts to get the news
      ContractsImplNewsApi contracts = new ContractsImplNewsApi("05f5d086cbc647e6bd2a902a8758af3b");

      // Get the news from internet
      List<News> newsList = contracts.retrieveNews(30);

      // Set the adapter
      runOnUiThread(() -> {
        newsAdapter.add(newsList);
      });
    });
  }
}