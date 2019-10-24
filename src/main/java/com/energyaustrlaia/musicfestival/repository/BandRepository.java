package com.energyaustrlaia.musicfestival.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.energyaustralia.musicfestival.recordlabel.Band;


@Repository
public interface BandRepository extends JpaRepository<Band, Integer> {

}
