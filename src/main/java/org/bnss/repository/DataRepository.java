package org.bnss.repository;

import org.bnss.domain.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
	
	Data findById(Long id);

}