package i.am.shiro.fragargpropdelegates

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import androidx.fragment.app.Fragment
import java.io.Serializable
import java.util.ArrayList
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Fragment.argBoolean() = argProperty(Bundle::putBoolean)
fun Fragment.argBooleanArray() = argProperty(Bundle::putBooleanArray)
fun Fragment.argBundle() = argProperty(Bundle::putBundle)
fun Fragment.argByte() = argProperty(Bundle::putByte)
fun Fragment.argByteArray() = argProperty(Bundle::putByteArray)
fun Fragment.argChar() = argProperty(Bundle::putChar)
fun Fragment.argCharArray() = argProperty(Bundle::putCharArray)
fun Fragment.argCharSequence() = argProperty(Bundle::putCharSequence)
fun Fragment.argCharSequenceArray() = argProperty(Bundle::putCharSequenceArray)
fun Fragment.argCharSequenceArrayList() = argProperty(Bundle::putCharSequenceArrayList)
fun Fragment.argDouble() = argProperty(Bundle::putDouble)
fun Fragment.argDoubleArray() = argProperty(Bundle::putDoubleArray)
fun Fragment.argFloat() = argProperty(Bundle::putFloat)
fun Fragment.argFloatArray() = argProperty(Bundle::putFloatArray)
fun Fragment.argInt() = argProperty(Bundle::putInt)
fun Fragment.argIntArray() = argProperty(Bundle::putIntArray)
fun Fragment.argIntArrayList() = argProperty(Bundle::putIntegerArrayList)
fun Fragment.argLong() = argProperty(Bundle::putLong)
fun Fragment.argLongArray() = argProperty(Bundle::putLongArray)
fun Fragment.argShort() = argProperty(Bundle::putShort)
fun Fragment.argShortArray() = argProperty(Bundle::putShortArray)
fun Fragment.argSize() = argProperty(Bundle::putSize)
fun Fragment.argSizeF() = argProperty(Bundle::putSizeF)
fun Fragment.argString() = argProperty(Bundle::putString)
fun Fragment.argStringArray() = argProperty(Bundle::putStringArray)
fun Fragment.argStringArrayList() = argProperty(Bundle::putStringArrayList)
inline fun <reified T : Parcelable> Fragment.argParcelable() = argProperty<T>(Bundle::putParcelable)
inline fun <reified T : Parcelable> Fragment.argParcelableArray() = argProperty<Array<T>?>(Bundle::putParcelableArray)
inline fun <reified T : Parcelable> Fragment.argParcelableArrayList() = argProperty<ArrayList<T>?>(Bundle::putParcelableArrayList)
inline fun <reified T : Parcelable> Fragment.argSparseParcelableArray() = argProperty<SparseArray<T>?>(Bundle::putSparseParcelableArray)
inline fun <reified T : Serializable> Fragment.argSerializable() = argProperty<T>(Bundle::putSerializable)

inline fun <reified T> Fragment.argProperty(crossinline setter: Bundle.(String, T) -> Unit) =
    object : ReadWriteProperty<Fragment, T?> {
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T? =
            arguments?.get(property.name) as? T

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
            val args = arguments ?: Bundle().also(::setArguments)
            if (value == null) {
                args.remove(property.name)
            } else {
                args.setter(property.name, value)
            }
        }
    }
