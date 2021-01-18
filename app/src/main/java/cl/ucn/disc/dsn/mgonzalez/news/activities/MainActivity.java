/*
 * Copyright 2020 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import cl.ucn.disc.dsn.mgonzalez.news.NewsItem;
import cl.ucn.disc.dsn.mgonzalez.news.R;
import cl.ucn.disc.dsn.mgonzalez.news.model.News;
import cl.ucn.disc.dsn.mgonzalez.news.services.ContractsImplNewsAPIs;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ModelAdapter;
import java.util.List;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

  private SwipeRefreshLayout swipeContainer;

  /**
   * OnCreate.
   *
   * @param savedInstanceState used to reload the app.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(R.layout.activity_main);

    // The Toolbar
    this.setSupportActionBar(findViewById(R.id.am_t_toolbar));

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
      ContractsImplNewsAPIs contracts = new ContractsImplNewsAPIs(
          "05f5d086cbc647e6bd2a902a8758af3b",
          "http://192.168.1.84:8000/"
      );

      // Get the news from internet
      List<News> newsList = contracts.retrieveNews(40);

      // Set the adapter
      runOnUiThread(() -> {
        newsAdapter.add(newsList);
      });
    });

    // Lookup the swipe container view
    swipeContainer = (SwipeRefreshLayout) findViewById(R.id.am_swl_refresh);
    // Setup refresh listener which triggers new data loading
    swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {

        // Your code to refresh the list here.
        // Make sure you call swipeContainer.setRefreshing(false)
        // once the network request has completed successfully.
        // Get the news in the background thread
        AsyncTask.execute(() -> {

          // Using the contracts to get the news
          ContractsImplNewsAPIs contracts = new ContractsImplNewsAPIs(
              "05f5d086cbc647e6bd2a902a8758af3b",
              "http://192.168.1.84:8000/"
          );

          // Get the news from internet
          List<News> newsList = contracts.retrieveNews(40);

          // Set the adapter
          runOnUiThread(() -> {
            // clear the items
            newsAdapter.clear();
            // add the news items
            newsAdapter.add(newsList);
          });
        });

        fastAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
      }
    });

    // Configure the refreshing colors
    swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

  }

  /**
   * Create the night mode menu option.
   *
   * @param menu the menu in the action bar.
   * @return true to display the menu, false to hide it.
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);

    // Change the label of the menu based on the state of the app
    int nightMode = AppCompatDelegate.getDefaultNightMode();
    if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
      menu.findItem(R.id.mm_it_nightMode).setTitle(R.string.day_mode);
    } else{
      menu.findItem(R.id.mm_it_nightMode).setTitle(R.string.night_mode);
    }

    return true;
  }

  /**
   * Handles options menu item clicks.
   *
   * @param item the item was pressed.
   * @return true since the item click va handle.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    // Check if the correct item was clicked
    if (item.getItemId() == R.id.mm_it_nightMode){
      // Get the night mode state of the app
      int nightMode = AppCompatDelegate.getDefaultNightMode();
      // Set the theme mode for the restarted activity
      if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
      }else{
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
      }

      // Recreate the activity for the theme change to take effect
      recreate();
    }

    return true;
  }
}