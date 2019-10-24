package com.energyaustrlaia.musicfestival.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energyaustralia.musicfestival.recordlabel.Festival;

public interface FestivalRepository  extends JpaRepository<Festival, Integer> {

}
