package com.example.ruru.sophiesblog.android.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by lyr on 2020/12/1 & content is
 */
@Entity
data class User(
  @Id var id: Long = 0,
  var name: String? = null
)