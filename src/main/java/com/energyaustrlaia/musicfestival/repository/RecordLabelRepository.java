package com.energyaustrlaia.musicfestival.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.energyaustralia.musicfestival.recordlabel.RecordLabel;

@Repository
public interface RecordLabelRepository extends JpaRepository<RecordLabel, Integer> {

}
