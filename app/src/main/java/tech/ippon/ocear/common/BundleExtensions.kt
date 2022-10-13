package tech.ippon.ocear.common

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

fun <T : Parcelable> Bundle.getParcelableObject(key: String, myClass: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= 33) {
        this.getParcelable(key, myClass)
    } else {
        this.getParcelable(key)
    }
}