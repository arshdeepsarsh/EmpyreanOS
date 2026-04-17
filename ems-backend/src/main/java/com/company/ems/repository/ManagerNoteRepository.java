package com.company.ems.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.ems.model.ManagerNote;

@Repository
public interface ManagerNoteRepository extends JpaRepository<ManagerNote, Long> {
    List<ManagerNote> findByManagerNameAndEmployeeNameOrderByIdDesc(String managerName, String employeeName);
}