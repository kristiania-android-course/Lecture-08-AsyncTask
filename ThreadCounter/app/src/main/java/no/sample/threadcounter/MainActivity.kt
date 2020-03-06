package no.sample.threadcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonRun.setOnClickListener{

            Thread( CountRunner() ).start()
        }
    }


    var runningThreads = 0
    // An inner class that implements Runnable interface
    inner class CountRunner: Runnable {

        override fun run() {

            runningThreads++

            this@MainActivity.runOnUiThread { // Run on ui thread using activity runOnUiThread
                textViewConsole.text =   "Running=$runningThreads"
            }

            Thread.sleep(1500 /* do not do anything for x milliseconds */)

            runningThreads--

            this@MainActivity.runOnUiThread { // Run on ui thread using activity runOnUiThread
                textViewConsole.text =   "Running=$runningThreads"
            }
        }

    }
}
