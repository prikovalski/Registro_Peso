package com.example.registrodepeso.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Medicao(var peso: Double, var data: Date):Parcelable
