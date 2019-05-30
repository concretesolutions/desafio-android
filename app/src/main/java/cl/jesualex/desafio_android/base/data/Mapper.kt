package cl.jesualex.desafio_android.base.data

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by jesualex on 16-03-19.
 */
abstract class Mapper<T1, T2> {
    abstract fun map(value: T1): T2

    abstract fun reverseMap(value: T2): T1

    fun map(values: List<T1>?): List<T2> {
        val returnValues = ArrayList<T2>()

        if (values != null) {
            for (value in values) {
                returnValues.add(map(value))
            }
        }

        return returnValues
    }

    fun reverseMap(values: List<T2>?): List<T1> {
        val returnValues = ArrayList<T1>()

        if (values != null) {
            for (value in values) {
                returnValues.add(reverseMap(value))
            }
        }

        return returnValues
    }

    fun map(values: Map<String, T1>?): Map<String, T2> {
        val returnValues = HashMap<String, T2>()

        if (values != null) {
            for ((key, value) in values) {
                returnValues[key] = map(value)
            }
        }

        return returnValues
    }

    fun reverseMap(values: Map<String, T2>?): Map<String, T1> {
        val returnValues = HashMap<String, T1>()

        if (values != null) {
            for ((key, value) in values) {
                returnValues[key] = reverseMap(value)
            }
        }

        return returnValues
    }
}
