package com.example.BossaBox.repository;

import com.example.BossaBox.domain.ToolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<ToolModel, Integer> {
    //query
    public ToolModel findByTitle(String title);
    public ToolModel findByLink(String link);
    public ToolModel findByDescription(String description);
}
