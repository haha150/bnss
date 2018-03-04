package org.bnss.repository;

import java.util.List;

import org.bnss.domain.Data;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCustomRepository {
	List<Data> getAllFiles();

}
