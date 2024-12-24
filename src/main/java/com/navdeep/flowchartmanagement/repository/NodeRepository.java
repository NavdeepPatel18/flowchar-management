package com.navdeep.flowchartmanagement.repository;

import com.navdeep.flowchartmanagement.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    Collection<Node> findAllByFlowchartId(Long flowchartId);
}
