package no.sample.asyncdownloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.net.URL
import java.net.URLConnection


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var link = "https://www.lakelouiseinn.com/wp-content/uploads/2019/01/LakeLouise2-1.jpg";

        ImageDownloader(this.imageView).execute(link)

    }


    inner class ImageDownloader(var imageView: ImageView) : AsyncTask<String, Int?, Bitmap?>() {

        override fun doInBackground(vararg urls: String):  Bitmap? {

            val url = URL(urls.get(0))
            val conection = url.openConnection()
            conection.connect()

            var fileSize =conection.contentLength

            val input = BufferedInputStream(url.openStream(), fileSize)
            var bitmap = BitmapFactory.decodeStream(input)

            return bitmap
        }

        override fun onProgressUpdate(vararg progress: Int?) {

        }

        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

}
