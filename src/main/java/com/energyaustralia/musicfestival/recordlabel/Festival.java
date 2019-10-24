package com.energyaustralia.musicfestival.recordlabel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All details about the Festival.")
@Entity
public class Festival {
	
	@Id
	@GeneratedValue
	private Integer festivalId;
	
	private String festivalName;
	private Date festivalDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Band band;
	
	public Integer getFestivalId() {
		return festivalId;
	}
	public void setFestivalId(Integer festivalId) {
		this.festivalId = festivalId;
	}
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	public Date getFestivalDate() {
		return festivalDate;
	}
	public void setFestivalDate(Date festivalDate) {
		this.festivalDate = festivalDate;
	}
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}
	@Override
	public String toString() {
		return "Festival [festivalId=" + festivalId + ", festivalName=" + festivalName + ", festivalDate="
				+ festivalDate + ", band=" + band + "]";
	}
}
