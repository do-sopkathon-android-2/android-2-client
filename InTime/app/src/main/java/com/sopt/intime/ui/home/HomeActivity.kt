package com.sopt.intime.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.sopt.intime.data.remote.response.DataContent
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

        setupOnClickListenerMorningTag()
        setupOnClickListenerLunchTag()
        setupOnClickListenerDinnerTag()
        setupOnClickListenerIcBack()

        homeBinding.clHomeMorning.setOnClickListener {
            homeViewModel.morningList.value = morningAdapter.currentList.toMutableList()
            val list = morningAdapter.currentList.toMutableList()
            list.add(DataContent((morningAdapter.currentList.size + 1).toLong(), ""))
            homeViewModel.morningList.value =  list
        }
        homeBinding.clHomeLaunch.setOnClickListener {
            homeViewModel.lunchList.value = lunchAdapter.currentList.toMutableList()
            val list = lunchAdapter.currentList.toMutableList()
            list.add(DataContent((lunchAdapter.currentList.size + 1).toLong(), ""))
            homeViewModel.lunchList.value =  list
        }
        homeBinding.clHomeDinner.setOnClickListener {
            homeViewModel.dinnerList.value = dinnerAdapter.currentList.toMutableList()
            val list = dinnerAdapter.currentList.toMutableList()
            list.add(DataContent((dinnerAdapter.currentList.size + 1).toLong(), ""))
            homeViewModel.dinnerList.value =  list
        }
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
        morningAdapter = MorningAdapter(::onMorningTodoListClick)
        homeBinding.rvHomeMorning.adapter = morningAdapter
        homeBinding.rvHomeMorning.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
    }

    private fun setupLunchRecyclerView() {
        lunchAdapter = LunchAdapter(::onLunchTodoListClick)
        homeBinding.rvHomeLaunch.adapter = lunchAdapter
        homeBinding.rvHomeLaunch.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
    }

    private fun setupDinnerRecyclerView() {
        dinnerAdapter = DinnerAdapter(::onDinnerTodoListClick)
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
        homeViewModel.morningList.observe(this) {
            morningAdapter.submitList(it.toList())
        }
        homeViewModel.lunchList.observe(this) {
            lunchAdapter.submitList(it.toList())
        }
        homeViewModel.dinnerList.observe(this) {
            dinnerAdapter.submitList(it.toList())
        }
    }

    private fun setTodoTimeTag(it: UserTimeDataResponse) {
        homeBinding.tvMorningTag.text = "아침 | ${it.morningStart}시"
        homeBinding.tvLaunchTag.text = "점심 | ${it.lunchStart}시"
        homeBinding.tvDinnerTag.text = "저녁 | ${it.dinnerStart}시"
        homeBinding.tvNightTag.text = "밤 | ${it.nightStart}시"
    }

    private fun onMorningTodoListClick(content: String) {
        homeViewModel.postTodoList(content, "morning")
    }
    private fun onLunchTodoListClick(content: String) {
        homeViewModel.postTodoList(content, "lunch")
    }
    private fun onDinnerTodoListClick(content: String) {
        homeViewModel.postTodoList(content, "dinner")
    }

    private fun setupOnClickListenerMorningTag() {
        homeBinding.tvMorningTag.setOnClickListener {
            with(homeBinding) {
                ivHomeBack.visibility = View.VISIBLE
                clHomeLaunch.visibility = View.GONE
                rvHomeLaunch.visibility = View.GONE
                clHomeDinner.visibility = View.GONE
                rvHomeDinner.visibility = View.GONE
                tvMorningTag.isEnabled = false
                tvLaunchTag.isEnabled = false
                tvDinnerTag.isEnabled = false
            }
            val colors = arrayOf("#C4BAFF", "#BFBFBF", "#BFBFBF", "#BFBFBF")
            pie.palette(colors)
            homeBinding.piechartHome.setChart(pie)
        }
    }

    private fun setupOnClickListenerLunchTag() {
        homeBinding.tvLaunchTag.setOnClickListener {
            with(homeBinding) {
                ivHomeBack.visibility = View.VISIBLE
                clHomeMorning.visibility = View.GONE
                rvHomeMorning.visibility = View.GONE
                clHomeDinner.visibility = View.GONE
                rvHomeDinner.visibility = View.GONE
                tvMorningTag.isEnabled = false
                tvLaunchTag.isEnabled = false
                tvDinnerTag.isEnabled = false
            }

            val colors = arrayOf("#BFBFBF", "#9B8AFF", "#BFBFBF", "#BFBFBF")
            pie.palette(colors)
            homeBinding.piechartHome.setChart(pie)
            homeBinding.piechartHome.invalidate()
        }
    }

    private fun setupOnClickListenerDinnerTag() {
        homeBinding.tvDinnerTag.setOnClickListener {
            with(homeBinding) {
                ivHomeBack.visibility = View.VISIBLE
                clHomeMorning.visibility = View.GONE
                rvHomeMorning.visibility = View.GONE
                clHomeLaunch.visibility = View.GONE
                rvHomeLaunch.visibility = View.GONE
                tvMorningTag.isEnabled = false
                tvLaunchTag.isEnabled = false
                tvDinnerTag.isEnabled = false
            }

            val colors = arrayOf("#BFBFBF", "#BFBFBF", "#6958CF", "#BFBFBF")
            pie.palette(colors)
            homeBinding.piechartHome.setChart(pie)
            homeBinding.root.requestLayout()
        }
    }

    private fun setupOnClickListenerIcBack() {
        homeBinding.ivHomeBack.setOnClickListener {
            with(homeBinding) {
                clHomeMorning.visibility = View.VISIBLE
                rvHomeMorning.visibility = View.VISIBLE
                clHomeLaunch.visibility = View.VISIBLE
                rvHomeLaunch.visibility = View.VISIBLE
                clHomeDinner.visibility = View.VISIBLE
                rvHomeDinner.visibility = View.VISIBLE
                ivHomeBack.visibility = View.GONE
                tvMorningTag.isEnabled = true
                tvLaunchTag.isEnabled = true
                tvDinnerTag.isEnabled = true
            }

            val colors = arrayOf("#C4BAFF", "#9B8AFF", "#6958CF", "#3D3B5C")
            pie.palette(colors)
            homeBinding.piechartHome.setChart(pie)
        }
    }

    companion object {
        fun from(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }
}

fun Activity.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view.windowToken, 0)
}
