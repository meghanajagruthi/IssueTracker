package com.elecnor.issue.tracker.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;



public class LineChart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6550339058117825057L;
	public Integer sum;
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	public Date reportedDate;
}
