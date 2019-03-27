package com.B17.sdssystem.data.entries

import java.io.Serializable

import com.google.gson.annotations.SerializedName


class SubTaskResponse : Serializable {

    @SerializedName("viewsubtasks")


    var viewsubtasks: List<ViewsubtasksItem>? = null
}