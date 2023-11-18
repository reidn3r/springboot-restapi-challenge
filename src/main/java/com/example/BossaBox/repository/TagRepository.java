package com.example.BossaBox.repository;

import com.example.BossaBox.domain.Tag.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagModel, Integer> {
    public List<TagModel> findAllByDescription(String tag);
    public List<TagModel> findToolIdByDescription(String tag);
}
