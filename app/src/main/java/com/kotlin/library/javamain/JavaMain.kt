package com.kotlin.library.javamain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Caojing on 2019/9/18.
 *  你不是一个人在战斗
 */
class JavaMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("开始执行程序")
            val num = intArrayOf(3, 56, 52, 23, 78, 65)
            println("原始数据：")
            for (i in num) {
                print("$i,")
            }
            println()
//            println("冒泡排序->排序之后的数据：")
//            for (i in getBubbleSort(num)) {
//                print("$i,")
//            }
            println("选择排序->排序之后的数据：")
            for (i in this.selectionSort(num)) {
                print("$i,")
            }
        }


        //冒泡排序，分别比较相邻的两个数据，如果前面的数大于后面的数，则互相交互位置
        private fun getBubbleSort(intArray: IntArray): IntArray {
            for (i in 1 until intArray.size) {
                for (j in 0..intArray.size - 1 - i) {
                    if (intArray[j] > intArray[j + 1]) {
                        val maxNum = intArray[j]
                        intArray[j] = intArray[j + 1]
                        intArray[j + 1] = maxNum
                    }
                }
            }
            return intArray
        }

        //选择排序，每次遍历找出数组中最小的数移到前面，直到结束，已经移动到最前面的数据不参与遍历
        private fun selectionSort(intArray: IntArray): IntArray {
            for (i in 0 until intArray.size - 1) {
                var minIndex = i
                for (j in i until intArray.size ) {
                    if (intArray[j] < intArray[minIndex]) {
                        minIndex = j
                    }
                }
                if (i != minIndex) {
                    val minNum = intArray[i]
                    intArray[i] = intArray[minIndex]
                    intArray[minIndex] = minNum
                }
            }
            return intArray
        }




        private fun kotlinCoroutines() {
            val num = AtomicLong()
            for (i in 1..1000000L) {
                GlobalScope.launch {
                    num.addAndGet(i)
                }
                println(num.get())
            }
        }

        private fun iO1() {
            //新建一个文件
            val file = File("abc.text")
            //新建一个输出流，像文件中写入数据
            val outputStream: OutputStream = FileOutputStream(file)
            //给输出流添加缓冲
            val bufferedOutputStream = BufferedOutputStream(outputStream)
            //像缓冲中写入数据
            bufferedOutputStream.write("我是缓存字节流数组".toByteArray())
            //将缓冲中的数据写入到文件
            bufferedOutputStream.flush()
            //关闭输出流
            outputStream.close()
            //关闭缓冲输出流
            bufferedOutputStream.close()
        }

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        private fun io2() {
            //新建一个文件
            val file = File("abc.text")
            //声明一个输入流
            var inputStream: FileInputStream? = null
            //声明一个
            var bufferedReader: BufferedReader? = null
            try {
                inputStream = FileInputStream(file)
                bufferedReader = BufferedReader(inputStream.bufferedReader() as Reader?)
                print(bufferedReader.readLine())
            } catch (e: Exception) {

            } finally {
                inputStream?.close()
                bufferedReader?.close()
            }
        }

    }
}