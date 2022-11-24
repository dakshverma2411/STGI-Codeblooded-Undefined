package com.undefined.codeblooded.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )
    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String mappingPath;

    @Column(nullable = false)
    private String inputPath;

    @Column(nullable = true)
    private String outputPath;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private ApplicationUser user;

}
