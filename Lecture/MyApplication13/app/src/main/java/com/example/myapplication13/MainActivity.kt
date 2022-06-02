package com.example.myapplication13

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //dates변수 선언
    var datas: MutableList<String>? = null
    lateinit var adapter : MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        // 순서1
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.setDecorFitsSystemWindows(false) //
            val controller = window.insetsController
            if(controller != null){
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior= WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
        else{ window.setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN )

        }
            // 어댑터나 다른 부분에서 오류가 나면 미싱컨텐트 때문일 수 있음

        //순서4  액티비티 리절트 런처 사용
        val requestLauncher : ActivityResultLauncher<Intent>
        = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //5-1 사용자 입력으로 test->result
            val d3 = it.data!!.getStringExtra("result")?.let {
                //순서8 투두 완성하기 -datas변수와 리사이틀러뷰에 추가하기
                datas?.add(it)
                adapter.notifyDataSetChanged() //아답터에 의해 입력변호가 바로 방향되도록
            }

//            Log.d("mo",d3!!)
        }

        //순서2 액티비티 호출 fab을 버튼으로 대체 가늘
        binding.fab.setOnClickListener{
            //인텐트 만들기
            val intent = Intent(this,AddActivity::class.java)
            //2-2 인텐트와 정보 함꼐 전달 가능
            intent.putExtra("data1","mobile")
            intent.putExtra("data2","app")
            //액티비비 시작을 요청
           // startActivity(intent)

        //순서 3add->main으로
            //startActivityForResult(intent,10)
        //순서4
            requestLauncher.launch(intent)

        }

        //순서8 객체를 선언 없이 let
        datas = savedInstanceState?.let {
            //번들 객체 전달
            it.getStringArrayList("데이터")?.toMutableList()
        }?:let {
            mutableListOf<String>() //null일때
        }
        //순서8 바인딩에 있는 리사이클러뷰에 있어서 매니저 필요
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        //아답터 설정
        adapter = MyAdapter(datas) //()안에는 항목들에 대한 정보가 들어감
        binding.mainRecyclerView.adapter = adapter
        //데코레이션
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this,LinearLayoutManager.VERTICAL) //구분선 생성
        )
    }

    //순서7 생명주기- bundle
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("데이터", ArrayList(datas))
    }



    //3-1메서드 추가 -리턴되고 돌아오는 거 - >순서4에 액티비티 런처 사용
   /*
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==10 && resultCode == RESULT_OK){
            //add액티비티가 전달하는 값
            val d3 = data?.getStringExtra("test")
            Log.d("mo",d3!!)
        }
    }
    */
}

//순서5투두앱 만들기

//순서 6 암시적 인텐트
/*mainfest에 인텐트 필터
TwoActivity만들고
* */