package com.sikaplun.kotlin.calculator

import java.math.BigInteger
import kotlin.math.*


class MathematicsCalculation {
    companion object {
        fun factorialCalculation(arg: String): String {
            return if (arg.contains(",")) {
                "invalid argument"
            } else {
                val num = arg.toInt()
                var factorial = BigInteger.ONE
                for (i in 1..num) {
                    factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
                }
                factorial.toString()
            }
        }

        fun exponentiation(arg: String, deg: String): Double {
            val x = arg.toDouble()
            val y = deg.toDouble()

            return x.pow(y)
        }

        fun calculateSqrt(arg: String): Double = sqrt(arg.toDouble())

        fun calculateSin(arg: String): Double = sin(arg.toDouble())

        fun calculateCos(arg:String): Double = cos(arg.toDouble())

        fun calculateTan(arg: String): Double = tan(arg.toDouble())

        fun calculateLog(arg: String): Double = ln(arg.toDouble())

    }

}