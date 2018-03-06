package org.bnss.service;

import java.util.List;

import org.bnss.domain.Data;


public interface DataService {
	
	List<Data> getAllFiles();
	Data findFileById(Long id);
	Data addFile(Data file);
	void deleteFile(Data file);

}
