/*
package com.zero.lib.kotlin.basic01

import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.concurrent.Executors
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random


typealias RunanbleType  = () -> Unit

fun javaThread(){
    //第一种写法
    Thread(object : Runnable{
        override fun run(){
            println("java Thread run ....")
        }
    }).start()
    //第二种写法
    val t1 = Thread{println("java Thread run1....")}
    t1.start()

    //第三种写法
    val r1 = { println("java Thread run2....")}
    Thread(r1).start()


    val executor = Executors.newCachedThreadPool()
    executor.execute { println("java Thread run3.....") }

    val r3: RunanbleType = { println("java Thread run4....")}

    executor.execute(r3)

}

fun launchTest(){
    runBlocking {
        println("使用runBlocking顶层函数")
    }

    val launch = GlobalScope.launch {
        println("使用GlobalScope 单例对象，可以直接调用launch开启协程")
    }

    val coroutineScope = CoroutineScope(object :CoroutineContext{
        override fun <R> fold(initial: R, operation: (R, CoroutineContext.Element) -> R): R = initial

        override fun <E : CoroutineContext.Element> get(key: CoroutineContext.Key<E>): E? = null

        override fun minusKey(key: CoroutineContext.Key<*>): CoroutineContext = this
    })
    coroutineScope.launch {
        println("自行通过CoroutineContext创建一个CoroutineScope对象，需要一个类型为CoroutineContext的参数")
    }
    coroutineScope.launch(Dispatchers.IO){
        println("Dispatchers.io")
    }
    GlobalScope.launch(Dispatchers.Default){
        println("Dispatchers.Main")
    }

    coroutineScope.async{ getAvatar()}
    coroutineScope.async { getConpanyLogo() }
}

suspend fun getInfo() = withContext(Dispatchers.IO){
    println("getInfo zero av lance")
}

public interface Context1{

    public interface Key<E: Element>

    public fun add(key: Key<*>):Context1

    fun<E:Element> getKey():E?

    public interface Element :Context1{
        val key:Key<*>

        override fun <E : Element> getKey(): E? {
            return this as E
        }
        override fun add(key: Key<*>): Context1 {
            return this
        }
    }
}

class CombContext : Context1{//链表
    override fun add(key: Context1.Key<*>): Context1 {
        return this
    }

    override fun <E : Context1.Element> getKey(): E? {
        return this as E?
    }

}

fun coroutineTest(){
    println("coroutineTest ${Thread.currentThread().name}")
    val coroutineScope = CoroutineScope(EmptyCoroutineContext)

    GlobalScope.launch {

    }

    coroutineScope.launch {
        val deferred = async {
            //异步
            getConpanyLogo()

        }
        launch {
            println("coroutineTest launch: ${Thread.currentThread().name}")
            withContext(Dispatchers.IO){
                println("coroutineTest withContext launch: ${Thread.currentThread().name}")
            getAvatar()
            }

            getInfo()
            delay(2000)
        }
        deferred.start()
       val result =  deferred.await()

        val job = GlobalScope.launch {
            println("0000")
            withContext(Dispatchers.IO){
                delay(5000)
                println("111")
            }
            withContext(Dispatchers.Unconfined){
                println("2222")
            }
            println("3333")
            delay(1000)
        }

        job.cancel()

        GlobalScope.launch {
            repeat(3){
                println("repeat")
            }
        }

    }

    Thread.sleep(10000)
}

fun main() {

    //java的thread
//    javaThread()
    //协程使用说明
//    launchTest()
    coroutineTest()




}

fun getRandom(): Int {
    return Random.nextInt(1,10)
}

suspend fun getAvatar(){
    delay((getRandom() * 100).toLong())
    println("getAvatar")
}

suspend fun getConpanyLogo():Int{
    delay((getRandom() * 100).toLong())
    println("getConpanyLogo")
    return 1
}*/
