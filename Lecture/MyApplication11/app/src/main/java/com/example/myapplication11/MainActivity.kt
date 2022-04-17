package com.example.myapplication11

/*appcompac 앱의 api 레벨 호환성을 해결 - 라이브러리 액션바, 개발자가 만드는 툴바
recyclerview 목록 화면을 구성
viewpager2  스와이프로 넘기는 화면
frigment  액티비티처럼 동작하는 뷰
drawlayout 서럽처럼 열리는 화면 구성
* */

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

//androidx로 AppCompatActivity를 상속받음
class MainActivity : AppCompatActivity() {
    /*
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
    */

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}  //binding을 전역변수로 선언 , by lazy로 초기값 늦추기
    lateinit var toggle : ActionBarDrawerToggle //토글 액션바를 위한 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //툴바 바인딩
        setSupportActionBar(binding.toolbar) //만약 툴바에서 오류가 나면 액션바와 튤바 둘 중 하나만 사용
        /*
        //액션바 드로우 토클
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
*/
    // ------------------프래그 먼트------------//
        /*
        //fragmentManager변수 선언
        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction() //fragmentManager가 가지고 있는 것에 대한 transaction

        //프래그먼트 클래스를 가져오는 변수
        var fragment1 = Fragment1()
        transaction.add(R.id.fragment_content,fragment1) //transaction 추가선언
        transaction.commit() //여기서 실행
*/

    /*
        //뷰페이저 방향
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //뷰페이지 바인딩
        binding.viewpager.adapter = MyFragementAdapter(this)
*/
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
                binding.tv1.text = p0
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean { //최종적으로 검색어가 입력될때마다
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       /*코틀린 액션바 사용시
        when(item.itemId){
            0 -> {
                true
            }
            1-> {
                true
            }
        }*/
        //xml파일 액션바 사용 시
        when(item.itemId){
            R.id.menu1 -> {
                //main.xml id가 tv1인 글자 색 변경
                binding.tv1.setTextColor(Color.RED) //binding 빨간 줄은 onCreate밖에서도 쓸 수 있도록 전역변수로 선언한다.
                true
            }
            R.id.menu2-> {
                true
            }
        }

        return super.onContextItemSelected(item)
    }

    /* 토글 toggle사용시
    * override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if(toogle.onOptionItemSelected(item)) return true

        when(item.itemId){
            R.id.menu1 -> {
                //main.xml id가 tv1인 글자 색 변경
                binding.tv1.setTextColor(Color.RED) //binding 빨간 줄은 onCreate밖에서도 쓸 수 있도록 전역변수로 선언한다.
                true
            }
            R.id.menu2-> {
                true
            }
        }
        return super.onContextItemSelected(item)
    }
    }
    * */
}
//액션바, 튤바, 프래그먼트
/*
1.액션바는 res->values->themes 폴더에서 themes.xml 설정 - 안드로이드 메인페스트.xml에 android:theme="@style/Theme.MyApplication11"> 설정으로 적용가능함
colorPrimary">@color/purple_500</item>이 액션바 색상
액션바 표시를 없애고 싶으면   themes.xml에서 <style name="Theme.MyApplication11" parent="Theme.MaterialComponents.DayNight.NoActionBar">

2.액션바 옵션 메뉴
첫 번째 방법은 Code->Generate->OverrideMethods->onCreateOptionsMenu
두 번째 방법은 res->New->Android Resource Directory->menu 리소스 메뉴에 menu Resouse file->생성된 xml로 <item>

3.액션바 옵션 메뉴 선택 되었을 때
Code->Generate->OverrideMethods->onOptionItemSelected(item)

4.액션바 없애기
themes에서 <style name="Theme.MyApplication11" parent="Theme.AppCompat.NoActionBar.NoActionBar">
*/
/*액션바와 튤바 둘 중 하나만 사용

툴바는 .xml <androidx.appcompat.widget.Toolbar>
*/
/*프래그먼트
androidx.fragment라이브러리에서 제공, 초기화 단계가 진행되면서 onAttach,onCreateView()함수 필수, 반환된 view객체가 화면에 출력
1.File->New->Fragment
2.main.xml에서 프래그먼트가 들어갈 자리만 만들어준다.
3.fragmentManger사용
4.프래그먼트 생명주기 함수 사용:,onCreate(),onStart()함수가 호출되는 순간 해당 프래그먼트가 호출
5.fragment 다른 점
*layoutInfalter 사용 방법: 프래그먼트의 parent는 메인액티비티를 의미함
return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

*val binding사용 방법:
val binding = Fragment1Binding.inflate(inflater, container, false)


-addToBackStack:프래그먼트가 화면에 보이지 않는 순간 제거하지 않고 저장했다가 다시 이용할 수 있는 기능
백스텝을 사용하면 onDestoryView함수까지만 호출
 */

/*리사이클러뷰
build.grale->implementation 'androidx.recyclerview:recyclerview:1.2.1'
 */

/*뷰페이저2 - 스와이프
build.grale->implementation
 */

/*드로어 레이아웃
툴바 영역에서 토클 클릭시 보이도록 =>ActionBarDraxerToggle ,액션이벤트 사용
뷰를 2개 선언하여 첫번째는 액티비티 화면에 출력, 두번째는 끌려 나오도록
액션바 드로우 토클 -toggle = ActionBarDrawerToggle() : res->strings.xml에 등록

*/