package com.navdeep.flowchartmanagement.repository;

import com.navdeep.flowchartmanagement.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    Collection<Node> findAllByFlowchartId(Long flowchartId);

    boolean existsByNameAndFlowchartId(String name, Long flowchartId);

    List<Node> findByFlowchartId(Long flowchartId);
}
