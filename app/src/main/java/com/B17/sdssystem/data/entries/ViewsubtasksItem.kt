package com.B17.sdssystem.data.entries


import com.google.gson.annotations.SerializedName

import java.io.Serializable


class ViewsubtasksItem : Serializable {

    @SerializedName("subtaskid")
    var subtaskid: String? = null

    @SerializedName("projectid")
    var projectid: String? = null

    @SerializedName("taskid")
    var taskid: String? = null
}



