package com.B17.sdssystem.data.entries;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SubTasksDetailResponse implements Serializable {

	@SerializedName("subtaskdesc")
	public String subtaskdesc;

	@SerializedName("endstart")
	public String endstart;

	@SerializedName("subtaskname")
	public String subtaskname;

	@SerializedName("subtaskid")
	public String subtaskid;

	@SerializedName("subtaskstatus")
	public String subtaskstatus;

	@SerializedName("startdate")
	public String startdate;

	@SerializedName("projectid")
	public String projectid;

	@SerializedName("taskid")
	public String taskid;
}