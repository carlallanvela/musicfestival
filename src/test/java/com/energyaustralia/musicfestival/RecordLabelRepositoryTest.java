package com.energyaustralia.musicfestival;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.energyaustralia.musicfestival.recordlabel.RecordLabel;
import com.energyaustrlaia.musicfestival.repository.RecordLabelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordLabelRepositoryTest {
	
	@Autowired
	private RecordLabelRepository recordLabelRepository;
	
	@Test
	public void testFindAll() {
		List<RecordLabel> allRecordLabels = recordLabelRepository.findAll();
		// 3 initial record label
		assertThat(allRecordLabels.size() == 3);
	}
	
	@Test
	public void testFindOne() {
		Optional<RecordLabel> recordLabel = recordLabelRepository.findById(10001);
		assertThat(recordLabel.get().getRecordLabelName() == "Record Label 1");
	}
	
	@Test
	public void testDelete() {
		Optional<RecordLabel> account = recordLabelRepository.findById(10001); 
		recordLabelRepository.deleteById(account.get().getRecordLabelId());
		Optional<RecordLabel> searchLabel = recordLabelRepository.findById(10001); 
		assertThat(searchLabel == null);
	}
}

