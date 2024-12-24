package com.navdeep.flowchartmanagement.repository;

import com.navdeep.flowchartmanagement.entity.Flowchart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowchartRepository extends JpaRepository<Flowchart, Long> {
}
