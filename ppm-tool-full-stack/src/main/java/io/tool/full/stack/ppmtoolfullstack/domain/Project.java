package io.tool.full.stack.ppmtoolfullstack.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author badrikant.soni on 21/01/19
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;
    
    private Date created_At;
    private Date updated_At;

    @PrePersist
    protected void OnCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void OnUpdate() {
        this.updated_At = new Date();
    }
}
