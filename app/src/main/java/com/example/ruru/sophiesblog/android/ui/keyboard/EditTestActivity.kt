package com.example.ruru.sophiesblog.android.ui.keyboard

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_edit_test.*

/**
 * EditText 获取到焦点 且 弹出软键盘
 */
class EditTestActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_test)

    //这两句没必要
    /* edit.isFocusable = true;
     edit.isFocusableInTouchMode = true;*/

    //必须：一
    edit.requestFocus()

    //必须：二 起作用
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

    //不起作用
    /*val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (currentFocus != null) {
      println("current $currentFocus")
      inputMethodManager.showSoftInput(currentFocus, 0)
    }*/


    var list = arrayListOf<String>("你好呀", "阳光")
    var sameStr = list.find {it.contains("阳光")}
    if (sameStr != null) {
      var pos = list.indexOf(sameStr)
      if (pos != - 1) {
        list[pos] = sameStr.replace("阳光", "")
      }
    }

    list.map {
      println("list item $it")
    }

    /**
     * A集合 removeAll B集合
     * 循环遍历A集合 如果B中包含该元素 ---> 则A集合删掉该元素
     */
    var listA = arrayListOf<String>()
    var listB = arrayListOf<String>("111", "222", "333", "444", "555")
    listA.removeAll(listB)
    println("removeAll $listA $listB")

    Util.versionName
    Util.get()
  }
}
