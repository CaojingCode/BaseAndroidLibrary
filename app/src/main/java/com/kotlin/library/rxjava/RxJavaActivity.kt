package com.kotlin.library.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.library.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.rxjava_activity.*
import io.reactivex.rxkotlin.Observables.zip as zip1

/**
 * Created by Caojing on 2019/8/15.
 * 你不是一个人在战斗
 */
class RxJavaActivity : AppCompatActivity() {

    private var TAG = "RxJavaActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rxjava_activity)
        buttonJust.setOnClickListener {

            //            val myObservable = getObservable()
//            val myObserver = getObserver()
//            myObservable.subscribe(myObserver)


            //RxKotlin 实现
            var list = listOf("1", "2", "3", "4", "5")

            val subscribe = list.toObservable()
                .filter {
                    it.toDouble() > 3.0
                }
                .subscribeBy(
                    onError = {},
                    onComplete = {},
                    onNext = {
                        Log.d(TAG, it)
                    })


//            val numbers = Observable.range(1, 6)
//
//            val strings = Observable.just("One", "Two", "Three", "Four", "Five", "Six")
//
//
//            val zipped = Observables.zip(strings, numbers) { s, n -> "$s $n" }
//            zipped.subscribe(::println)
        }
    }


    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(s: String) {
                Log.d(TAG, "onNext: $s")
            }


            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }
}
