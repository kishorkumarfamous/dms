package com.dmsBackend.repository;

import com.dmsBackend.entity.DocumentHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentHeaderRepository extends JpaRepository<DocumentHeader,Integer> {
    List<DocumentHeader> findByIsApprovedFalse();
    List<DocumentHeader> findByIsApprovedTrue();

}
