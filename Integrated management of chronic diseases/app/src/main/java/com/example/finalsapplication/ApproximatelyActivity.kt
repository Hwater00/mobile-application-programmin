package com.example.finalsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalsapplication.databinding.ActivityApproximatelyBinding
import com.example.finalsapplication.databinding.ActivityMainBinding
import org.w3c.dom.Text

class ApproximatelyActivity : AppCompatActivity() {
    lateinit var rv_phone_book: RecyclerView
    lateinit var phoneBookListAdapter: PhoneBookListAdapter
    lateinit var persons: ArrayList<Person>
    var datas: MutableList<String>? = null
    lateinit var adapter : MyAdapter
    lateinit var search_view_phone_book: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityApproximatelyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        rv_phone_book = findViewById(R.id.rv_phone_book)
        search_view_phone_book = findViewById(R.id.search_view_phone_book)

        search_view_phone_book.setOnQueryTextListener(searchViewTextListener)

        persons = tempPersons()
        setAdapter()

        val requestLauncher:ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val d3 = it.data!!.getStringExtra("result")?.let{
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }

        }
        binding.fab.setOnClickListener{
            rv_phone_book.setVisibility(View.GONE)

            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("data1", "약")
            intent.putExtra("data2", "입력")
            //startActivity(intent)
            //startActivityForResult(intent, 10) // 호출
            requestLauncher.launch(intent)
        }
        datas = savedInstanceState?.let{
            it.getStringArrayList("mydatas")?.toMutableList()
        } ?:let{
            mutableListOf<String>()
        }

        // savedInstanceState에서 내용을 가져온 후에 리사이클러뷰를 설정
        binding.approxRecycerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(datas)
        binding.approxRecycerView.adapter = adapter

    }



    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("mydatas", ArrayList(datas))
    }

    //SearchView 텍스트 입력시 이벤트
    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                phoneBookListAdapter.filter.filter(s)
                Log.d("TAG", "SearchVies Text is changed : $s")
                return false
            }
        }
    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        rv_phone_book.layoutManager = LinearLayoutManager(this)
        phoneBookListAdapter = PhoneBookListAdapter(persons, this)
        rv_phone_book.adapter = phoneBookListAdapter
    }
    fun tempPersons(): ArrayList<Person> {
        var tempPersons = ArrayList<Person>()
        tempPersons.add(Person(1, "트라젠타듀오정", "1000mg"))
        tempPersons.add(Person(2, "타이레놀정", "160"))
        tempPersons.add(Person(3, "슈스타정", "10"))
        tempPersons.add(Person(4, "트원스타정", "80mg"))
        tempPersons.add(Person(5, "글로스타정", "10mg"))
        tempPersons.add(Person(6, "자누비아정", "10mg"))
        tempPersons.add(Person(7, "아르바정", "20mg"))
        tempPersons.add(Person(8, "아마필정", "2mg"))
        tempPersons.add(Person(9, "릭시아나정", "60mg"))
        tempPersons.add(Person(10, "아시니아나정", "30mg"))
        tempPersons.add(Person(11, "릭시아나정", "30mg"))
        return tempPersons
    }

}
