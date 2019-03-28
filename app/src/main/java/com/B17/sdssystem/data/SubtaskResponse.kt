package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class SubtaskResponse( @SerializedName("project sub task") val subtaskList : List<Subtask> = ArrayList()) {

}