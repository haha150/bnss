package org.bnss.service;

import java.util.List;

import org.bnss.domain.Data;
import org.bnss.repository.DataCustomRepository;
import org.bnss.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	DataRepository dataRepo;
	
	@Autowired
	DataCustomRepository dataCustomRepo;
	

	@Override
	public List<Data> getAllFiles() {
		return dataCustomRepo.getAllFiles();
	}

	@Override
	public Data findFileById(Long id) {
		return dataRepo.findById(id);
	}

	@Override
	public Data addFile(Data data) {
		return dataRepo.save(data);
	}


}
