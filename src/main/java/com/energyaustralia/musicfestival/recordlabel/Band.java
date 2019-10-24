package com.energyaustralia.musicfestival.recordlabel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

/**
 * Band Bean.
 * @author Carl Allan Vela
 *
 */
@ApiModel(description="All details about the Band.")
@Entity
public class Band {
	
	@Id
	@GeneratedValue
	private Integer bandId;
	private String bandName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private RecordLabel recordLabel;
	
	@OneToMany(mappedBy="band")
	private List<Festival> festivals;	
	
	public Integer getBandId() {
		return bandId;
	}

	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public RecordLabel getRecordLabel() {
		return recordLabel;
	}

	public void setRecordLabel(RecordLabel recordLabel) {
		this.recordLabel = recordLabel;
	}

	public List<Festival> getFestivals() {
		return festivals;
	}

	public void setFestivals(List<Festival> festivals) {
		this.festivals = festivals;
	}

	@Override
	public String toString() {
		return "Band [bandId=" + bandId + ", bandName=" + bandName + ", festivals=" + festivals + ", recordLabel="
				+ recordLabel + "]";
	}
}
