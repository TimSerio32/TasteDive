package serio.tim.android.com.tastedive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val spinnerItems: List<String> = Constants.SPINNER_ITEMS

    private var type: String = spinnerItems[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_loadingIndicator.hide()

        setUpSpinner()
    }

    fun requestOnClick(v: View) {

    }

    private fun setUpSpinner() {
        spinner.setItems(spinnerItems)

        spinner.setOnItemSelectedListener { _, position, _, _ ->
            type = spinnerItems[position]
        }
    }
}
