package com.undefined.codeblooded.repositories;

import com.undefined.codeblooded.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GeneratorRepository extends JpaRepository<Project, Long> {

}
