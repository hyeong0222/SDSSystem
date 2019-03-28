package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName

data class UpdateTask (@SerializedName("msg") var msg : List<String> = ArrayList<String>()) {
}