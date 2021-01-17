/*
 * Copyright 2021 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsn.mgonzalez.news.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * A class for showing a <code>Toast</code> from background processes using a
 * <code>Handler</code>.
 *
 * @author kaolick
 */
public class ToastHandler
{

  // General attributes
  private Context mContext;
  private Handler mHandler;

  /**
   * Class constructor.
   *
   * @param _context
   *            The <code>Context</code> for showing the <code>Toast</code>
   */
  public ToastHandler(Context _context)
  {
    this.mContext = _context;
    this.mHandler = new Handler();
  }

  /**
   * Runs the <code>Runnable</code> in a separate <code>Thread</code>.
   *
   * @param _runnable
   *            The <code>Runnable</code> containing the <code>Toast</code>
   */
  private void runRunnable(final Runnable _runnable)
  {
    Thread thread = new Thread()
    {
      public void run()
      {
        mHandler.post(_runnable);
      }
    };

    thread.start();
    thread.interrupt();
    thread = null;
  }

  /**
   * Shows a <code>Toast</code> using a <code>Handler</code>. Can be used in
   * background processes.
   *
   * @param _resID
   *            The resource id of the string resource to use. Can be
   *            formatted text.
   * @param _duration
   *            How long to display the message. Only use LENGTH_LONG or
   *            LENGTH_SHORT from <code>Toast</code>.
   */
  public void showToast(final int _resID, final int _duration)
  {
    final Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        // Get the text for the given resource ID
        String text = mContext.getResources().getString(_resID);

        Toast.makeText(mContext, text, _duration).show();
      }
    };

    runRunnable(runnable);
  }

  /**
   * Shows a <code>Toast</code> using a <code>Handler</code>. Can be used in
   * background processes.
   *
   * @param _text
   *            The text to show. Can be formatted text.
   * @param _duration
   *            How long to display the message. Only use LENGTH_LONG or
   *            LENGTH_SHORT from <code>Toast</code>.
   */
  public void showToast(final CharSequence _text, final int _duration)
  {
    final Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        Toast.makeText(mContext, _text, _duration).show();
      }
    };

    runRunnable(runnable);
  }

}
