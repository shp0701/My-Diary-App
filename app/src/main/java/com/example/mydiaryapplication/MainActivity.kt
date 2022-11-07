package com.example.mydiaryapplication

import android.app.Activity
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.PagerAdapter
import com.example.mydiaryapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var oneFragment: OneFragment
    lateinit var twoFragment: TwoFragment
    lateinit var threeFragment: ThreeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //액션바를 툴바로 대체하기
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_opend, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val pagerAdapter = PagerAdapter(this)
        val title = mutableListOf<String>("메모", "가계부", "목표")
        oneFragment = OneFragment()
        twoFragment = TwoFragment()
        threeFragment = ThreeFragment()

        pagerAdapter.addFragment(oneFragment, title[0])
        pagerAdapter.addFragment(twoFragment, title[1])
        pagerAdapter.addFragment(threeFragment, title[2])

        binding.viewpager.adapter = pagerAdapter

        //탭레이아웃 뷰페이저를 연결함
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = title.get(position)
        }.attach()

        //확장된 플로팅 액션기능을 확장 및 축소를 진행하기
        binding.efab.shrink()
        binding.efab.setOnClickListener {
            when (binding.efab.isExtended) {
                true -> binding.efab.shrink()
                false -> binding.efab.extend()
            }
            Toast.makeText(applicationContext, "확장tab선택", Toast.LENGTH_SHORT).show()
        }

        binding.navigationview.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navi_menu_calender ->
                    Toast.makeText(applicationContext,"캘린더 클릭", Toast.LENGTH_SHORT).show()

                R.id.navi_menu_photo ->
                    Toast.makeText(applicationContext, "포토다이어리 클릭", Toast.LENGTH_SHORT).show()

                R.id.navi_menu_list ->
                    Toast.makeText(applicationContext, "할 일 리스트 클릭", Toast.LENGTH_SHORT).show()


            }
            true
        }
        //drawerLayout navigationView 클릭이벤트 설정


    }

    //메뉴옵션 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(searchText: String?): Boolean {
                Log.d("My Diary", "${searchText}")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }//end of onCreateOptionsMenu

    //메뉴옵션 클릭했을때 이벤트발생
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        when(item.itemId){
            R.id.menu_save->{Toast.makeText(applicationContext, "저장", Toast.LENGTH_SHORT).show()}
            R.id.menu_search->{Toast.makeText(applicationContext, "검색", Toast.LENGTH_SHORT).show()}
            R.id.menu_load->{Toast.makeText(applicationContext, "불러오기", Toast.LENGTH_SHORT).show()}
            R.id.menu_open->{Toast.makeText(applicationContext, "열기", Toast.LENGTH_SHORT).show()}
        }
        return super.onOptionsItemSelected(item)
    }

}

