package com.example.homework.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.adapter.RestaurantListAdapter
import com.example.homework.base.BaseActivity
import com.example.homework.utils.UiUtils
import com.example.homework.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.activity_restaurant_list.*

class RestaurantListActivity : BaseActivity() {

    private val viewModel: RestaurantListViewModel by viewModels()
    private val adapter by lazy { RestaurantListAdapter() }
    var radius: Int = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerview()
        observe()
        setListener()
        fetchData()
        seekBar()
    }

    private fun fetchData() {
        viewModel.fetchRestaurant("restaurant", "new york", radius)
    }

    private fun setListener() {
        srl_refresh.setOnRefreshListener { fetchData() }
    }

    private fun setupRecyclerview() {
        rv_content.adapter = adapter
        rv_content.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val gap = UiUtils.dp2px(view.context, 8)
                outRect.left = gap
                outRect.right = gap
                outRect.top = gap
                outRect.bottom = gap
            }
        })
    }

    /**
     * observe the fetch result
     */
    private fun observe() {
        viewModel.restaurantResult.observe(this, Observer { result ->

            if (result == null) {
                Toast.makeText(this, R.string.failed_fetch, Toast.LENGTH_LONG).show()
            } else {
                adapter.setDatas(result)
            }
        })
        viewModel.refresh.observe(
            this,
            Observer { srl_refresh.post { srl_refresh.isRefreshing = it } })
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_restaurant_list
    }


    override fun setTitle(): String {
        return "Restaurant List"
    }

    private fun seekBar() {
        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                radius = seek.progress
                // write custom code for progress is stopped
                Toast.makeText(
                    this@RestaurantListActivity,
                    "Radius is: " + seek.progress,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}