/*
 * Copyright 2020 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsn.mgonzalez.news.model.News;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mikepenz.fastadapter.items.ModelAbstractItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * The NewsItem to show in the list.
 *
 * @author Matthew Gonzalez-Mansilla
 */
public final class NewsItem extends ModelAbstractItem<News, NewsItem, NewsItem.ViewHolder> {

  /**
   * The ZonedDateTime formatter.
   */
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("HH:mm d.LLL.yyyy");

  /**
   * The Constructor.
   *
   * @param news to show.
   */
  public NewsItem(@NotNull News news) {super(news);}

  /**
   * @param view used to build the ViewHolder.
   * @return the ViewHolder to the views.
   */
  @NonNull
  @Override
  public ViewHolder getViewHolder(@NotNull View view) {
    return new ViewHolder(view);
  }

  @Override
  public int getType() {
    return R.id.am_rv_news;
  }

  /**
   * @return the layout to use.
   */
  @Override
  public int getLayoutRes() {
    return R.layout.item_news;
  }

  /**
   * Bind the holder with the model.
   *
   * @param holder to use.
   * @param payloads ?.
   */
  @Override
  public void bindView(@NotNull ViewHolder holder, @NotNull List<Object> payloads){
    super.bindView(holder, payloads);

    // Setting the holder
    holder.title.setText(getModel().getTitle());
    holder.author.setText(getModel().getAuthor());
    holder.source.setText(getModel().getSource());
    holder.description.setText(getModel().getDescription());
    holder.publishedAt.setText(FORMATTER.format(getModel().getPublishedAt()));
    holder.urlImage.setImageURI(getModel().getUrlImage());

  }

  /**
   * Clean the holder.
   *
   * @param holder to clean.
   */
  @Override
  public void unbindView(@NotNull ViewHolder holder){
    super.unbindView(holder);

    holder.title.setText(null);
    holder.author.setText(null);
    holder.source.setText(null);
    holder.description.setText(null);
    holder.publishedAt.setText(null);
    holder.urlImage.setImageURI((Uri) null);
    

  }

  /**
   * The ViewHolder pattern.
   */
  protected static class ViewHolder extends RecyclerView.ViewHolder{

    protected TextView title;
    protected TextView author;
    protected TextView source;
    protected TextView description;
    protected TextView publishedAt;
    protected SimpleDraweeView urlImage;

    public ViewHolder(@NonNull View view) {
      super(view);

      this.title = view.findViewById(R.id.in_tv_title);
      this.author = view.findViewById(R.id.in_tv_author);
      this.source = view.findViewById(R.id.in_tv_source);
      this.description = view.findViewById(R.id.in_tv_description);
      this.publishedAt = view.findViewById(R.id.in_tv_published_at);
      this.urlImage = view.findViewById(R.id.in_sd_image);
    }
  }
}
