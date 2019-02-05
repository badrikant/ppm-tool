package io.tool.full.stack.ppmtoolfullstack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author badrikant.soni on 02/02/19
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message = "Please include a project summary")
    private String summary;
    private String acceptanceCritera;
    private String status;
    private Integer priority;
    private Date dueDate;

    //ManyToOne with Backlog
    // Use Case: There can be multiple projectTasks and those will belong to one and only one backlog.
    // CascadeType.REFRESH - it tells that on deleting the projectTasks, backlog will be refreshed automatically and says deleted projectTask is no longer exists.
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    // "backlog_id" will not be updatable and nullable
    @JsonIgnore // in case of infinite recursion issue
    private Backlog backlog;

    @Column(updatable = false)
    private String projectIdentifier;
    private Date create_At;
    private Date udpate_At;
}
