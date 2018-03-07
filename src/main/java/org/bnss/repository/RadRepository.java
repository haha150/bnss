package org.bnss.repository;

import org.bnss.domain.RadCheck;
import org.bnss.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadRepository extends JpaRepository<RadCheck, Long> {
	
	RadCheck findById(Long id);

}