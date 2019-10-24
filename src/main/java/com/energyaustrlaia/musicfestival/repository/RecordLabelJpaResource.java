package com.energyaustrlaia.musicfestival.repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.energyaustralia.musicfestival.exception.BandNotFoundException;
import com.energyaustralia.musicfestival.exception.RecordLabelNotFoundException;
import com.energyaustralia.musicfestival.recordlabel.Band;
import com.energyaustralia.musicfestival.recordlabel.Festival;
import com.energyaustralia.musicfestival.recordlabel.RecordLabel;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RecordLabelJpaResource {
	
	@Autowired
	private RecordLabelRepository recordLabelRepository;
	
	@Autowired
	private BandRepository bandRepository;

	
	@GetMapping(path="/jpa/recordLabels")
	public List<RecordLabel> retrieveAllRecordLabels() {
		return recordLabelRepository.findAll();
	}
	
	@GetMapping(path="/jpa/recordLabel/{recordLabelId}")
	public Optional<RecordLabel> retrieveRecordLabel(@PathVariable int recordLabelId) {
		Optional<RecordLabel> recordLabel = recordLabelRepository.findById(recordLabelId);
		
		if (!recordLabel.isPresent()) {
			throw new RecordLabelNotFoundException("recordLabelId: " + recordLabelId);
		}

		// HATEOAS
		// enables us to add links using method		
		//"all-users", SERVER_PATH + "/recordLabels"
		Resource<RecordLabel> resource = new Resource<RecordLabel>(recordLabel.get());
		
		// Enable creates links from methods
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllRecordLabels());
		
		resource.add(linkTo.withRel("all-recordlabels"));
		
		return recordLabel;
	}

	@PostMapping(path="/jpa/recordLabel")
	public ResponseEntity<Object> createRecordLabel(@Valid @RequestBody RecordLabel recordLabel) {
		RecordLabel savedRecordLabel = recordLabelRepository.save(recordLabel);

		// Return a status called CREATED
		// Set a URI of the created Resource to the Response
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{recordLabelId}")
			.buildAndExpand(savedRecordLabel.getRecordLabelId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/recordLabel/{recordLabelId}")
	public void deleteRecordLabel(@PathVariable int recordLabelId) {
		RecordLabel recordLabel = recordLabelRepository.getOne(recordLabelId);
		
		// Remove Bands First
		bandRepository.deleteAll(recordLabel.getBands());
		recordLabelRepository.deleteById(recordLabelId);
	}
	
	@GetMapping(path="/jpa/recordLabel/{recordLabelId}/bands")
	public List<Band> retrieveAllBands(@PathVariable int recordLabelId) {
		Optional<RecordLabel> recordLabelOptional = recordLabelRepository.findById(recordLabelId);
		if (!recordLabelOptional.isPresent()) {
			throw new RecordLabelNotFoundException("recordLabelId:" + recordLabelId);
		}
		return recordLabelOptional.get().getBands();
	}
	
	@GetMapping(path="/jpa/recordLabel/{recordLabelId}/bands/{bandId}")
	public Optional<Band> retrieveBand(@PathVariable int recordLabelId,
			@PathVariable int bandId) {
		Optional<Band> band = bandRepository.findById(bandId);
		
		if (!band.isPresent()) {
			throw new BandNotFoundException("bandId: " + bandId);
		}

		Resource<Band> resource = new Resource<Band>(band.get());
		
		// Enable creates links from methods
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllRecordLabels());
		
		resource.add(linkTo.withRel("all-bands"));
		
		return band;
	}
	
	@PostMapping(path="/jpa/recordLabel/{recordLabelId}/bands")
	public ResponseEntity<Object> createBand(@PathVariable int recordLabelId,
			@RequestBody Band band) {
		
		Optional<RecordLabel> recordLabelOptional = recordLabelRepository.findById(recordLabelId);
		
		if (!recordLabelOptional.isPresent()) {
			throw new RecordLabelNotFoundException("recordLabelId:" + recordLabelId);
		}

		RecordLabel recordLabel = recordLabelOptional.get();
		band.setRecordLabel(recordLabel);
		bandRepository.save(band);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{recordLabelId}")
			.buildAndExpand(band.getBandId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/recordLabel/{recordLabelId}/band/{bandId}")
	public void deleteBand(@PathVariable int bandId) {
		bandRepository.deleteById(bandId);
	}
	
	@GetMapping(path="/jpa/recordLabel/{recordLabelId}/band/{bandId}/festivals")
	public List<Festival> retrieveAllFestivals(@PathVariable int recordLabelId, int bandId) {
		Optional<RecordLabel> recordLabelOptional = recordLabelRepository.findById(recordLabelId);
		Optional<Band> bandOptional = bandRepository.findById(bandId);
		
		if (!recordLabelOptional.isPresent()) {
			throw new RecordLabelNotFoundException("recordLabelId:" + recordLabelId);
		}
		
		if (!bandOptional.isPresent()) {
			throw new BandNotFoundException("bandId:" + bandId);
		}
		return bandOptional.get().getFestivals();
	}
}
