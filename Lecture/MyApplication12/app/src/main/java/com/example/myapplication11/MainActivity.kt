package com.example.myapplication11



import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TableLayout
import androidx.appcompat.app.ActionBarDrawerToggle
//import 안드로이드x에 있는 SearchView로 수정한다
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication11.databinding.ActivityMainBinding
import com.example.myapplication11.databinding.Fragment2Binding
import com.google.android.material.tabs.TabLayoutMediator

//androidx로 AppCompatActivity를 상속받음
class MainActivity : AppCompatActivity() {

    //뷰페이저를 위한 adapter 클래스 만들기
    class MyFragementAdapter(activity:FragmentActivity):FragmentStateAdapter(activity){
        val fragments: List<Fragment>//프래그먼트 묶음을 위한 배열 변수 선언
        init{
            fragments = listOf(Fragment1(),Fragment2(),Fragment3())
        }
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {  //전달받은 프래그먼트 리스트 중 position에 해당하는 것을 리턴
            return fragments[position]
        }
    }


    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}  //binding을 전역변수로 선언 , by lazy로 초기값 늦추기
    lateinit var toggle : ActionBarDrawerToggle //토글 액션바를 위한 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //툴바 바인딩
        setSupportActionBar(binding.toolbar) //만약 툴바에서 오류가 나면 액션바와 튤바 둘 중 하나만 사용

        //액션바 드로우 토클
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //fragmentManager변수 선언
       // val fragmentManager : FragmentManager = supportFragmentManager
        //val transaction : FragmentTransaction = fragmentManager.beginTransaction() //fragmentManager가 가지고 있는 것에 대한 transaction

        //프래그먼트 클래스를 가져오는 변수
        //var fragment1 = Fragment1()
        //transaction.add(R.id.fragment_content,fragment1) //transaction 추가선언
        //transaction.commit() //여기서 실행

        //뷰페이저 방향
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //뷰페이지 바인딩
        binding.viewpager.adapter = MyFragementAdapter(this)


    //순서2 탭레이아웃 배치
        TabLayoutMediator(binding.tab1,binding.viewpager){
            tab,position -> tab.text ="TAB ${position+1}"
        }.attach()



    }

    //옵션 메뉴 생성 방법1
    //Code->Generate->OverrideMethods->onCreateOptionsMenu오버라이딩
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //코틀린 코드 액션바
        //val menuItem1 : MenuItem? = menu?.add(0,0,0,"메뉴 노출 문장") ()2번째 숫자가 id

        //xml파일 액션바
        menuInflater.inflate(R.menu.menu_main,menu)

        //검색 메뉴에서 사용자가 어떤 값을 입력했는지 확인하는 메뉴
        val menuSearch = menu?.findItem(R.id.menu_search)//menuItem을 가져올 변수 선언
        val searchView = menuSearch?.actionView as SearchView //연결된 액션뷰를 가져올 변수 선언
        //내용을 받기 setOnQueryTextListener
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean { //내용이 변경될 때마다
                //binding.tv1.text = p0 텍스트
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean { //최종적으로 검색어가 입력될때마다
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)

    }


    // 토글 toggle사용시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) return true

        when(item.itemId){
            R.id.menu1 -> {
                //main.xml id가 tv1인 글자 색 변경
               // binding.tv1.setTextColor(Color.RED) //binding 빨간 줄은 onCreate밖에서도 쓸 수 있도록 전역변수로 선언한다.
                true
            }
            R.id.menu2-> {
                true
            }
        }
        return super.onContextItemSelected(item)
    }


}

/*앱바
* build.grale->implementation 'com.google.android.material:material:1.5.0'
* 앱바를 화면에 배치하기 위해서 .xml파일에 앱바 <com.google.android.material.appbar.AppBarLayout>
*앱바 내에 툴바 포함
*
* 순서 2 접히는 컬렉싱
* <androidx.coordinatorlayout.widget.CoordinatorLayout>

* */
/*탭 레이아웃 구현 -프래그먼트2
순서1 탭 보이기
순서2 탭레이아웃 배치
* */
