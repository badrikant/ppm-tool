package io.tool.full.stack.ppmtoolfullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    // this is a database layer constraints and it will only invoke when trying to save/persist the object in the DB.
    @Column(updatable = false, unique = true)
    // it should be unique and shouldn't be able to edit/update once it gets created.
    private String projectIdentifier;

    @NotBlank(message = "Description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd") // need to have better date format instead "2019-01-22T04:30:17.134+0000"
    private Date startDate;

    @JsonFormat(pattern = "yyyy-mm-dd") // need to have better date format instead "2019-01-22T04:30:17.134+0000"
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd") // need to have better date format instead "2019-01-22T04:30:17.134+0000"
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd") // need to have better date format instead "2019-01-22T04:30:17.134+0000"
    private Date updated_At;

    // use case : A project can have only one backlog
    // CascadeType.ALL - on creating/updating/deleting the project, backlog object will also be creating/updating/deleting.
    // FetchType.EAGER - fetch Backlog table without any explicit request by app.
    // mappedBY - value must be match with project object in Backlog class.
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    // during the fetch of project, backlog will increase the size of payload so decided to have separate call to fetch project backlog. Hence we simply ignore the backlog object in fetch time.
    private Backlog backlog;

    // Usecase : projects may have only one user.
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String projectLeader;

    @PrePersist
    protected void OnCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void OnUpdate() {
        this.updated_At = new Date();
    }
}
