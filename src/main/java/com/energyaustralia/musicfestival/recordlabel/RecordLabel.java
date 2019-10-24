package com.energyaustralia.musicfestival.recordlabel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Record Label Bean.
 * @author Carl Allan Vela
 *
 */
@ApiModel(description="All details about the Record Label.")
@Entity
public class RecordLabel {
	
	@Id
	@GeneratedValue
	private Integer recordLabelId;
	
	@Size(min = 2, message = "Record Label Name should have at least 2 characters.")
	@ApiModelProperty(notes = "Record Label Name should have at least 2 characters.")
	private String recordLabelName;
	
	@OneToMany(mappedBy="recordLabel")
	List<Band> bands;

	public Integer getRecordLabelId() {
		return recordLabelId;
	}

	public void setRecordLabelId(Integer recordLabelId) {
		this.recordLabelId = recordLabelId;
	}

	public String getRecordLabelName() {
		return recordLabelName;
	}

	public void setRecordLabelName(String recordLabelName) {
		this.recordLabelName = recordLabelName;
	}

	public List<Band> getBands() {
		return bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

	@Override
	public String toString() {
		return "RecordLabel [recordLabelId=" + recordLabelId + ", recordLabelName=" + recordLabelName + ", bands="
				+ bands + "]";
	}
}
