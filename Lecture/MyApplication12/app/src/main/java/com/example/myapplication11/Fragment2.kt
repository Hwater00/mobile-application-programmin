package com.example.myapplication11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication11.databinding.Fragment2Binding
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //프래그먼트 뷰
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_2, container, false)

    //순서1
        //뷰 바인딩으로 xml가져오기 //프래그먼트는 inflate에 인자 3개 들어감!
        val binding = Fragment2Binding.inflate(inflater, container, false)

        //탭 버튼을 선택했을 때 이벤트 처리, 오버라이드 3개
        binding.tabs.addOnTabSelectedListener(
            object: TabLayout.OnTabSelectedListener {

                //탭이 선택됐을 때
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //TODO("Not yet implemented")

                    //프래그먼트를 관리하는 프래그먼트 매니저 가져오기기
                    //activity에서는 supportFragmentManager로 가져왔는데, 프래그먼트에서는 직접 가져옴
                    val transaction = fragmentManager?.beginTransaction()

                    //when으로 selected 되어있는 tab의 정보 가져옴
                    //text로 분별해서 tabcontent를 변경함
                    when(tab?.text) {
                        "tab1" -> transaction?.replace(R.id.tabContent, Fragment21())
                        "tab2" -> transaction?.replace(R.id.tabContent, Fragment22())
                        "tab3" -> transaction?.replace(R.id.tabContent, Fragment23())
                    }
                    //트랜젝션에서는 commit 무조건 해야됨!
                    transaction?.commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    //TODO("Not yet implemented")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    //TODO("Not yet implemented")
                }

            }
        )
    //순서1
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}