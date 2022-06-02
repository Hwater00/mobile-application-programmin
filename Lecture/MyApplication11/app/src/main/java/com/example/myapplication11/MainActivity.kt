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

 //뷰페이저 순서 1
    //뷰페이저를 위한 adapter 클래스 만들기
    class MyFragementAdapter(activity:FragmentActivity):FragmentStateAdapter(activity){
        //프래그먼트 묶음을 위한 배열 변수 선언
        val fragments: List<Fragment>
        //3개의 프래그먼트 등록
        init{
            fragments = listOf(Fragment1(),Fragment2(),Fragment3())
        }


        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
        //전달받은 프래그먼트 리스트 중 position에 해당하는 것을 리턴
            return fragments[position]
        }
    }

    //순서 3 binding을 전역변수로 선언 , by lazy로 초기값 늦추기
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    //드로우 순서 2 토클
    //토글 액션바를 위한 변수 선언
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //순서 1 val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //순서 6 툴바 바인딩
        setSupportActionBar(binding.toolbar) //만약 툴바에서 오류가 나면 액션바와 튤바 둘 중 하나만 사용

    //드로우 순서 2 토클

        //액션바 드로우 토클
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

    // ------------------순서 7 프래그 먼트------------//
        /* 프래그먼트 코드 배치
        //순서7 fragmentManager변수 선언
        val fragmentManager : FragmentManager = supportFragmentManager
        //제어시작 변수
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()

        //순서7 프래그먼트 외부에 있는 클래스를 가져오는 변수
        var fragment1 = Fragment1()
        transaction.add(R.id.fragment_content,fragment1) //불러올 프래그먼트
        transaction.commit() //여기서 실행
*/


        //뷰페이저 방향
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    //뷰페이저 순서 1
        //뷰페이지 바인딩
        binding.viewpager.adapter = MyFragementAdapter(this)

    }


    //Code->Generate->OverrideMethods->onCreateOptionsMenu오버라이딩
    //순서3 //옵션 메뉴 생성 방법1
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //순서3 코틀린 코드 액션바
        //순서3
        //val menuItem1 : MenuItem? = menu?.add(0,0,0,"메뉴 노출 문장") ()2번째 숫자가 id
        // val menuItem2 : MenuItem? = menu?.add(0,1,0,"메뉴2")

        //순서 3 xml파일 액션바
        menuInflater.inflate(R.menu.menu_main,menu)

        //순서4 서치뷰
        //검색 메뉴에서 사용자가 어떤 값을 입력했는지 확인하는 메뉴
        val menuSearch = menu?.findItem(R.id.menu_search)//menuItem을 가져올 변수 선언
        val searchView = menuSearch?.actionView as SearchView //연결된 액션뷰를 가져올 변수 선언

        //순서4 서치뷰
        //내용을 받기 setOnQueryTextListener, 오버라이드 2개
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean { //내용이 변경될 때마다
                //binding.tv1.text = p0 텍스트
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean { //최종적으로 검색어가 입력될때마다
                return true
            }
        }


        )
        //순서3 때 작성 이후로 수정 X
        return super.onCreateOptionsMenu(menu)

    }
    /*
    //순서3 옵션 선택되었을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       /*순서3 코틀린 액션바 사용시
        when(item.itemId){
            0 -> {
                true
            }
            1-> {
                true
            }
        }*/
        //순서3 xml파일 액션바 사용 시
        when(item.itemId){
            R.id.menu1 -> {
                //main.xml id가 tv1인 글자 색 변경
                //binding.tv1.setTextColor(Color.RED) //binding 빨간 줄은 onCreate밖에서도 쓸 수 있도록 전역변수로 선언한다.
                true
            }
            R.id.menu2-> {
                true
            }
        }

        return super.onContextItemSelected(item)
    }*/

    //드로우 순서 2 토클
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


//액션바, 튤바, 프래그먼트
/*------순서2
1.액션바는 res->values->themes 폴더에서 themes.xml 설정 - 안드로이드 메인페스트.xml에 android:theme="@style/Theme.MyApplication11"> 설정으로 적용가능함
colorPrimary">@color/purple_500</item>이 액션바 색상
#액션바 표시를 없애고 싶으면   themes.xml에서 <style name="Theme.MyApplication11" parent="Theme.MaterialComponents.DayNight.NoActionBar">

-------순서3
2.옵션 메뉴 만들기
첫 번째 방법은 Code->Generate->OverrideMethods->onCreateOptionsMenu
두 번째 방법은 res->New->Android Resource Directory->menu 리소스 메뉴에 menu Resouse file->생성된 xml로 <item>
menu_main.xml이다.
3.액션바 옵션 메뉴 선택 되었을 때
Code->Generate->OverrideMethods->onOptionItemSelected(item)

------순서4
4.서치뷰 연결
menu->menu_main.xml에 app:actionViewClass="androidx.appcompat.widget.SearchView" 추가로 서치뷰
변수 2개 추가
내용을 받기 setOnQueryTextListener{
오버라이드 2개}
import 안드로이드x에 있는 SearchView로 수정한다

5.액션바 없애기
themes에서 <style name="Theme.MyApplication11" parent="Theme.AppCompat.NoActionBar.NoActionBar">

-----순서6 툴바
툴바는 .xml <androidx.appcompat.widget.Toolbar>
setSupportActionBar(binding.toolbar)
*/


/*프래그먼트
-------순서7
1.File->New->Fragment
2.main.xml에서 프래그먼트가 들어갈 자리만 만들어준다. -LinearLayout
변수 선언
프래그먼트 클래스를 가져오는 변수
------
3.fragmentManger사용
4.프래그먼트 생명주기 함수 사용:,onCreateView(),onStart()함수가 호출되는 순간 해당 프래그먼트가 호출
5.fragment 다른 점
*layoutInfalter 사용 방법: 프래그먼트의 parent는 메인액티비티를 의미함
return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

androidx.fragment라이브러리에서 제공, 초기화 단계가 진행되면서 onAttach,onCreateView()함수 필수, 반환된 view객체가 화면에 출력
*val binding사용 방법:
val binding = Fragment1Binding.inflate(inflater, container, false)


-addToBackStack:프래그먼트가 화면에 보이지 않는 순간 제거하지 않고 저장했다가 다시 이용할 수 있는 기능
백스텝을 사용하면 onDestoryView함수까지만 호출
 */

/*리사이클러뷰: 프래그먼트1
리사이클순서1
build.grale->implementation 'androidx.recyclerview:recyclerview:1.2.1'
리사이클러 뷰 배치를 결정한 레이아웃 파일을 새로 만든다.=item_recyclerview.xml
뷰 홀더, 어댑터 클래스 선언
리사이클뷰를 위한 Adapter에 3개의 오버라이딩 함수
onCreateView에서 목록에 대한 정보를 만들고 마이아답터로 전달

리사이클 순서2:  다양한 레이아웃 사용
리사이클 순서3 : 데코레이션
데코레이션을 위한 클래스MyDecoration, 오버라이드 3개,
class Fragment1 의
override fun onCreateView(안에  데코레이션을 리사이클러 뷰에 반영
   binding.recyclerview.addItemDecoration(MyDecoration(activity as Context))
        )
 */

/*뷰페이저2 - 스와이프
build.grale->implementation
액티비티메인.xml에 <androidx.viewpager2.widget.ViewPager2>
뷰페이저를 위한 adapter 클래스 만들기, 오버라이드 2개
프래그먼트 확보
override fun onCreate(savedInstanceState: Bundle?) 아래에
//뷰페이지 바인딩  binding.viewpager.adapter = MyFragementAdapter(this)
 */

/*드로어 레이아웃
툴바 영역에서
토클 클릭시 보이도록 =>ActionBarDraxerToggle ,액션이벤트 사용
뷰를 2개 선언하여 첫번째는 액티비티 화면에 출력, 두번째는 끌려 나오도록
액션바 드로우 토클 -toggle = ActionBarDrawerToggle() : res->strings.xml에 등록

*/

/*앱바
* build.grale->implementation 'com.google.android.material:material:1.5.0'
* 앱바를 화면에 배치하기 위해서 .xml파일에 앱바 <com.google.android.material.appbar.AppBarLayout
*
* -코디네이터 레이아웃 - 뷰끼리 상호 작용
* */