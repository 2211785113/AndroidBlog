package com.example.ruru.sophiesblog.java.threaddemo.execute

import android.util.SparseArray
import kotlinx.coroutines.*

/**
 * Created by lyr on 2020/7/27 & content is
 * 延时去重执行器：
 * - 确保同一代码块在一定时间内 只执行一次
 */
class DelayDistinctExecutor {
  private val distinctSyncArray = SparseArray<Job?>()
  private val executorScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

  /**
   * 延时去重执行
   */
  fun submit(key: Int, delayTimeMillis: Long, block: suspend CoroutineScope.() -> Unit): Job {
    // 取消未来得及执行
    distinctSyncArray[key]?.cancel()

    val job = executorScope.launch {
      delay(delayTimeMillis)
      block(this)
      distinctSyncArray.remove(key)
    }
    distinctSyncArray.append(key, job)
    return job
  }

  companion object {
    private val instance = DelayDistinctExecutor()
    fun submit(key: Int, delayTimeMillis: Long, block: suspend CoroutineScope.() -> Unit): Job = instance.submit(key, delayTimeMillis, block)

    fun newInstance() = DelayDistinctExecutor()
  }
}