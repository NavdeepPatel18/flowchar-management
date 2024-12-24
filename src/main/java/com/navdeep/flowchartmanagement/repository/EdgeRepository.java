package com.navdeep.flowchartmanagement.repository;

import com.navdeep.flowchartmanagement.entity.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    Collection<Edge> findAllByFlowchartId(Long id);

    Collection<Edge> findAllByFlowchartIdAndSource(Long flowcharId, String source);

    List<Edge> findByFlowchartId(Long flowchartId);
}
