package serio.tim.android.com.tastedive.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import serio.tim.android.com.tastedive.constants.Constants
import serio.tim.android.com.tastedive.R
import serio.tim.android.com.tastedive.application.TasteDiveApplication
import java.util.LinkedHashMap
import javax.inject.Inject
import serio.tim.android.com.tastedive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    @Inject
    lateinit var tasteDiveAdapter: TasteDiveAdapter

    private val spinnerItems: List<String> = Constants.SPINNER_ITEMS

    private var type: String = spinnerItems[0]

    private lateinit var handlers: MainHandlers

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as TasteDiveApplication).appComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        handlers = MainHandlers(this)
        binding.handler = handlers

        progress_loadingIndicator.hide()

        setUpSpinner()
        initRecyclerView()
        observeOutcomeLiveData()
    }

    private fun setUpSpinner() {
        spinner.setItems(spinnerItems)

        spinner.setOnItemSelectedListener { _, position, _, _ ->
            type = spinnerItems[position]
        }
    }

    private fun initRecyclerView() {
        recycler.hasFixedSize()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        binding.recycler.layoutManager = layoutManager
    }

    private fun observeOutcomeLiveData() {
        viewModel.getOutcomeObservable().observe(this, Observer {
            when(it) {
                is Outcome.Progress -> {
                    text_error.visibility = View.GONE
                    progress_loadingIndicator.show()
                    recycler.visibility = View.GONE
                }

                is Outcome.Success -> {
                    text_error.visibility = View.GONE
                    progress_loadingIndicator.hide()
                    tasteDiveAdapter.setResultList(it.data)
                    binding.recycler.adapter = tasteDiveAdapter
                    recycler.visibility = View.VISIBLE
                }

                is Outcome.Failure -> {
                    progress_loadingIndicator.hide()
                    recycler.visibility = View.GONE
                    text_error.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun getKeywordTitle(): String {
        return edittext_keywordTitle.text.toString()
    }

    inner class MainHandlers(val context: Context) {
        fun requestOnClick(v: View) {
            tasteDiveAdapter.clear()

            val map = LinkedHashMap<String, String>()

            map["q"] = getKeywordTitle()
            map["k"] = Constants.API_KEY
            if(!(type.equals("Mixed"))) {
                map["type"] = type.toLowerCase()
            }

            viewModel.getSimilarData(map)
            recycler.visibility = View.VISIBLE
        }
    }
}
