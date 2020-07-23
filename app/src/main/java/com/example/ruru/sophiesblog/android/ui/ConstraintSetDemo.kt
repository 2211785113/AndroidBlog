package com.example.ruru.sophiesblog.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_contraint_set.*

/** ConstraintSet 用法 */
class ConstraintSetDemo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_contraint_set)

    var set = ConstraintSet()
    set.clone(cl_layout)

    set.connect(R.id.tv2, ConstraintSet.TOP, R.id.tv3, ConstraintSet.BOTTOM)
    set.applyTo(cl_layout)
  }
}
