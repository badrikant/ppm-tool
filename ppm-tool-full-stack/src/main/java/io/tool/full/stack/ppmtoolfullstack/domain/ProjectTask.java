package io.tool.full.stack.ppmtoolfullstack.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(updatable = false)
    private String projectIdentifier;
    private Date create_At;
    private Date udpate_At;
}
