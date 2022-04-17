package com.example.myapplication10

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.myapplication10.databinding.ActivityMainBinding
import com.example.myapplication10.databinding.DialogInputBinding

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener{
            //Toast.makeText(this, "첫 번째 버튼의 토스트", Toast.LENGTH_SHORT).show()
            //변수를 이용하여 토스트
            val toast = Toast.makeText(this, "첫 번째 버튼의 토스트", Toast.LENGTH_SHORT)
            //변수를 사용하면 토스트 텍스트나, 듀레이션 ,토스트 위치 변경이 가능하다
            toast.setText("수정된 토스트입니다.")//글자
            toast.duration= Toast.LENGTH_LONG //길이

            toast.addCallback(
                object : Toast.Callback(){
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("app","토스트 사라짐")
                    }

                    override fun onToastShown() {
                        super.onToastShown()

                    }
                }

            )
            toast.show() //1.토스트 콜백함수
            /* toast.addCallback(
                object : Toast.Callback(){ object를 넣고 여기에 callback함수
                   override fun onToastHidden(), override fun onToastShown() 을 넣어준다
                   빨간줄이 나온다면 버전 호환성 문제이기 때문에 class아래에 @RequiresApi을 넣어준다 : @RequiresApi(Build.VERSION_CODES.R)
                }
            )
            * */

        }
        binding.btn2.setOnClickListener{
            DatePickerDialog(this, object:DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    Log.d("","$p1,${p2+1},$p3")
                }
            },2022,3,15).show(

            )
        }
        binding.btn3.setOnClickListener{
            TimePickerDialog(this,
                object :TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        Log.d("","$p1 시,$p2 분")
                    }
                                                          }
                ,13,0,true).show() // true는 24시간 시간표시, false는 12시간 시간표시
        }

        //btn4 공통 리스너 변수 선언
        val eventHandler = object :DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) { //p1은 무엇을 클릭했는지를 전달받는다. p1에 따라서 다르게
                if(p1==DialogInterface.BUTTON_POSITIVE){
                }
                else if (p1==DialogInterface.BUTTON_NEGATIVE){
                }
                //이벤트 핸들러와 버튼의 리스너 연결 listener: 변수인 eventHandler
            }
        }
        binding.btn4.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("알림창 테스트")//제목 붙이기
                setIcon(android.R.drawable.ic_dialog_info)//아이콘 넣기 android.R.drawable에 아이콘 위치
                setMessage("종료인가요?")//메시지
                setPositiveButton("yes",eventHandler) //PositiveButton은 (첫번째 자리 yes나확인 ,두번째 자리 리스너)
                setNegativeButton("NO",eventHandler)
                setNeutralButton("more",null)
                setCancelable(false) //뒤로가기 버튼 x
                show()//화면에 보여주기 위해서는 반드시 show()
            }.setCanceledOnTouchOutside(false) //알림창 밖에 눌렬을때 사라지지 x
        }
        //목록이기에 배열로 변수 선언
        val items = arrayOf<String>("사과","딸기","복숭아","토마토")
        binding.btn5.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("목록 선택")
                setItems(items,object :DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {  //p1은 아이템 배열 변수 중 몇 번째인가 p1값은 0,1,2,3 중 1개
                       Log.d("app","${items[p1]}")
                    }
                })//목록을 대화상자에 띄우기 위해서 setItems(목록변수,각각을 클릭했을 때 인터페이스)
                setPositiveButton("닫기",null)
                show()//화면에 보여주기 위해서는 마지막에 반드시 show()

            }
        }
        binding.btn6.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("멀티 아이템 체크박스") //멀티초이스
                setMultiChoiceItems(items, booleanArrayOf(false,true,false ,false), //booleanArrayOf(인자는 배열 변수, 첫번째 초기값,두번/재 초기값) 선택=true, 비선택=false
                    object :DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) { //DialogInterface는 지금 알림창 인터페이스
                            Log.d("app","${items[p1]} ${if(p2) "선택" else "해제"}")  //p1은 몇 번째 아이템에 해당하는가, p2는 클릭에 따른 첫번째 값
                        }
                    }
                )
                setPositiveButton("닫기",null)
                show()//화면에 보여주기 위해서는 마지막에 반드시 show()
            }
        }
        binding.btn7.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("싱글 아이템 라디오버튼")
                setSingleChoiceItems(items, 1//초기값
                    ,object :DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {  //p1은 아이템 배열 변수 중 몇 번째인가 p1값은 0,1,2,3 중 1개
                        Log.d("app","${items[p1]}")
                    }
                })
                setPositiveButton("닫기",null)
                show()//화면에 보여주기 위해서는 마지막에 반드시 show()
            }
        }
        //Layoutinflater로  dialog_input.xml 바인딩 변수
        val dialogbinding =DialogInputBinding.inflate(layoutInflater)

        //AlertDialog를 미리 만들고 show만 해서 보이도록 수정
        val arlet= AlertDialog.Builder(this)
            .setTitle("라디오그룹 입력")
            .setView(dialogbinding.root)
            .setPositiveButton("닫기",null)
            .create()
        binding.btn8.setOnClickListener{
            arlet.show()
        }
        /* 버튼을 누를때마다 뷰 다시 불러옴
        binding.btn8.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("라디오그룹 입력")
                //만들어 놓은 다이얼로그 input.xml을 가져온다.
                setView(dialogbinding.root)
                setPositiveButton("닫기",null)
                show()//화면에 보여주기 위해서는 마지막에 반드시 show()
            }
        }*/
    }
}

/*
* 1.날짜 입력 데이터피커
* DatePickerDialog(this,
* 오브젝트 넣어주고 object:DatePickerDialog.OnDateSetListener{ 메인액티비티는 context가 this,this 다음 대괄호 안에 오버라이드 함수 넣기
* override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        사용자가 입력한 3개의 int값이 오도로 함. }
    },초기값 년,월은 month는 0부터 11이라서 실제월-1월,일 ).show()
*
* 2.시간 입력 타임피커
* TimePickerDialog(this,object :TimePickerDialog.OnTimeSetListener{override onTimeSet
* override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) { }
* 함수} ,13,0,true).show()
*
* 3.알림창 AlertDialog
* *AlertDialog.Builder(this).run { 3가지 버튼이 들어간다
    * setPositiveButton() yes
    * setNegativeButton() no
      setNeutralButton() more
      setCancelable(false) //뒤로가기 버튼 x
  }.setCanceledOnTouchOutside(false) //알림창 밖에 눌렬을때 사라지지 x
* -1.yes,no,more알림창
    3가지 버튼에 공통 리스너
* -2.목록을 대화상자에 띄우는 알림창
     object :DialogInterface.OnClickListener
    setItems(items,object :DialogInterface.OnClickListener{
       override fun onClick(p0: DialogInterface?, p1: Int) {  //p1은 아이템 배열 변수 중 몇 번째인가 p1값은 0,1,2,3 중 1개}
* -3체크박스가 나오는 멀티 초이스 알림창
    setMultiChoiceItems()//멀티초이스
* -4라디오버튼이 나오는 멀티 초이스
      setSingleChoiceItems()
* */

/*커스텀 다이얼로그 AlertDialog을 사용
-Layoutinflater 클래스로 레이아웃 xml파일을 코틀린에서 뷰객체로 만들어줌,  뷰바인딩에서 사용했음
 AlertDialog를 미리 만들고 show만 해서 보이도록 수정 .create()
* */