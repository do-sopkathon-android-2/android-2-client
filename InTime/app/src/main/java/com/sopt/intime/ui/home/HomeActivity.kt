package com.sopt.intime.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.sopt.intime.databinding.ActivityHomeBinding
import com.sopt.intime.ui.home.adapter.HomeViewModel
import com.sopt.intime.ui.home.adapter.MorningAdapter

class HomeActivity : AppCompatActivity() {
    private val homeBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeBinding.root)

        setupAnyPieChart()
        setupRecyclerView()
    }

    private fun setupAnyPieChart() {

        val pie = AnyChart.pie()
        val data = listOf(
            ValueDataEntry("☀️", 2),
            ValueDataEntry("점심", 3),
            ValueDataEntry("저녁", 2),
            ValueDataEntry("밤", 6)
        )

        pie.data(data)
        pie.hovered().fill("none")
        pie.background("white")
        pie.legend().enabled(false)

        pie.labels().apply {
            format("{%X}")
            fontColor("#000000")
            fontSize(20)
            position("inside")
        }

        homeBinding.piechartHome.setChart(pie)
    }

    private fun setupRecyclerView() {
        val morningAdapter = MorningAdapter()
        homeBinding.rvHomeMorning.adapter = morningAdapter
        homeBinding.rvHomeMorning.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        morningAdapter.submitList(homeViewModel.mockData)
    }
}
