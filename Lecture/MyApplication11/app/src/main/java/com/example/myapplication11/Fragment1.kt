package com.example.myapplication11

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.databinding.ActivityMainBinding
import com.example.myapplication11.databinding.Fragment1Binding
import com.example.myapplication11.databinding.ItemRecyclerviewBinding
import java.util.zip.Inflater

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */

//리사이클러 제어를 위한 코드
//뷰 홀더, 어댑터, 레이아웃 메니저 선언 필요
//리사이클러 뷰의 뷰 홀더 만들기
//item.recyclerview.xml에 있는 걸 받아오기
//뷰 바인딩에 의해서 클래스로 변경됨 -> ItemRecyclerviewBinding
//리사이클뷰 클래스
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) //뷰 홀더 준비
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){ //리사이클뷰를 위한 Adapter
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { //마이아답터에서 뷰 홀더를 생성하여 리사이클에 필요 형태로
        //xml에 있는 뷰 객체도 같이 전달
        //layoutInfalter는 메인에서 제공됨, 하지만 필요하니 만듦= 프래그 먼트의 parent는 메인액티비티를 의마함
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {//데이터와 뷰 홀더를 연결시키는 작업을 함
        val binding = (holder as MyViewHolder).binding // MyViewHolder의 binding정보를 갖는 변수 선언
        binding.itemTv.text = datas[position] //바인딩에 있는 뷰 객체 중  id를 통해 선언한 객체 데이터 정보 넣기, position


    }
}

//데코레이션을 위한 클래스
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        //ondraw는 리사이클러 뷰 항목이 그려지기 전에 호출되는 함수
        //따라서 배경색 먼저 칠하는 느낌
        //캠퍼스에 드로우하기-비트맵 함수
        //c.drawBitmap(BitmapFactory.decodeResource(context.resources - 리소스 불러오기, R.drawable.stadium), 0f, 0f - 위치, null)

    }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //이미지가 가운데에 오게 하기
        val width = parent.width
        val height = parent.height

        //이미지에 대한 정보 변수
        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val d_width = dr?.intrinsicWidth
        val d_height = dr?.intrinsicHeight

        //스마트폰의 크기 / 2 - 이미지 크기 / 2
        val left = width / 2 - d_width?.div(2) as Int
        val top = height / 2 - d_height?.div(2) as Int

        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), left.toFloat(), top.toFloat(), null) //Float형태

    }

    //항목 하나하나를 호출해서 꾸며줌
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(10, 10, 10, 0) //항목의 크기
        view.setBackgroundColor(Color.parseColor("#49c1ff")) //parseColor()색깔 직접 지정
        ViewCompat.setElevation(view, 20.0f)//3차원적으로 보이게 하기 setElevation
        //리사이클러븅 반영되도록 binding
    }
}

class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //프래그먼트 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //onCreateView에서 목록에 대한 정보를 만들고 마이아답터로 전달
    //반드시 작성!! //프래그먼트에 화면을 구성
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //데이터 저장 변수 선언
        //목록에 대한 정보를 만들고 MyAdapter로 전달하는 코드
        val datas= mutableListOf<String>()
        for (i in 1..9){
            datas.add("item $i")
        }
        //프래그먼트 화면 구성
        //프래그먼트1.xml의 뷰 객체를 가져올 수 있는 viewBinding
        val binding = Fragment1Binding.inflate(inflater, container, false)
        /*리니어 레이아웃을 사용하는 리사이클러 뷰를 만들겠다.
        화면에 보여주기 전에 레이아웃에 대한 설정 필요 변수 :val layoutManager
        리니어 레이아웃 가로로 변경 -xml파일 layout_width ="wrap_content"
        //val layoutManager = LinearLayoutManager(activity)
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        */
        /*그리드 레이아웃으로 변경
        가로로 변경 -xml파일 layout_width ="wrap_content"
        //val layoutManager = GridLayoutManager(activity, 2)//열 개수*/
        binding.recyclerview.layoutManager =LinearLayoutManager(activity)
        binding.recyclerview.adapter = MyAdapter(datas) //어댑터에 대한 설정
        //MyAdapter(datas) // MyAdapter(datas)를 반드시 {}과 줄 차이나게
        binding.recyclerview.addItemDecoration(MyDecoration(activity as Context)) //데코레이션을 리사이클러 뷰에 반영
        return binding.root
        /*return inflater.inflate(R.layout.fragment_1, container, false)*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}