package com.sopt.intime.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.sopt.intime.data.remote.response.UserTimeDataResponse
import com.sopt.intime.databinding.ActivityHomeBinding
import com.sopt.intime.ui.home.adapter.dinner.DinnerAdapter
import com.sopt.intime.ui.home.adapter.lunch.LunchAdapter
import com.sopt.intime.ui.home.adapter.morning.MorningAdapter
import kotlin.math.abs

class HomeActivity : AppCompatActivity() {
    private val homeBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var pie: Pie

    private lateinit var morningAdapter: MorningAdapter
    private lateinit var lunchAdapter: LunchAdapter
    private lateinit var dinnerAdapter: DinnerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeBinding.root)
        setupMorningRecyclerView()
        setupLunchRecyclerView()
        setupDinnerRecyclerView()
        pie = AnyChart.pie()
        homeViewModel.getUserTime()
        observeData()

        homeViewModel.getTodoListAll()
    }

    private fun setupAnyPieChart(time: UserTimeDataResponse) {
        val data = listOf(
            ValueDataEntry("", abs(time.lunchStart - time.morningStart)),
            ValueDataEntry("", abs(time.dinnerStart - time.lunchStart)),
            ValueDataEntry("", abs(time.dinnerEnd - time.dinnerStart)),
            ValueDataEntry("", abs(time.morningStart - time.dinnerEnd))
        )

        pie.data(data)
        pie.hovered().fill("none")
        pie.background("white")
        pie.legend().enabled(false)

        val colors = arrayOf("#C4BAFF", "#9B8AFF", "#6958CF", "#3D3B5C")
        pie.palette(colors)

        pie.labels().apply {
            format("{%X}")
            fontColor("#000000")
            fontSize(20)
            position("inside")
        }

        homeBinding.piechartHome.setChart(pie)
    }

    private fun setupMorningRecyclerView() {
        morningAdapter = MorningAdapter()
        homeBinding.rvHomeMorning.adapter = morningAdapter
        homeBinding.rvHomeMorning.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
    }

    private fun setupLunchRecyclerView() {
        lunchAdapter = LunchAdapter()
        homeBinding.rvHomeLaunch.adapter = lunchAdapter
        homeBinding.rvHomeLaunch.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
    }

    private fun setupDinnerRecyclerView() {
        dinnerAdapter = DinnerAdapter()
        homeBinding.rvHomeDinner.adapter = dinnerAdapter
        homeBinding.rvHomeDinner.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
    }

    private fun observeData() {
        homeViewModel.userTimeData.observe(this) {
            // 시간들 불러옴
            homeBinding.tvHomeMorningStartTime.text = "${it.morningStart}시"
            setupAnyPieChart(it)
            setTodoTimeTag(it)
        }
        homeViewModel.todoListAll.observe(this) {
            morningAdapter.submitList(it.morning)
            lunchAdapter.submitList(it.lunch)
            dinnerAdapter.submitList(it.dinner)
        }
    }

    private fun setTodoTimeTag(it: UserTimeDataResponse) {
        homeBinding.tvMorningTag.text = "아침 | ${it.morningStart}시"
        homeBinding.tvLaunchTag.text = "점심 | ${it.lunchStart}시"
        homeBinding.tvDinnerTag.text = "저녁 | ${it.dinnerStart}시"
        homeBinding.tvNightTag.text = "밤 | ${it.nightStart}시"
    }
}
