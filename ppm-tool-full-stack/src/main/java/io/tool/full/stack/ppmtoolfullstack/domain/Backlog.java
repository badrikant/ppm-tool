package io.tool.full.stack.ppmtoolfullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author badrikant.soni on 02/02/19
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;

    //OneToOne with project
    @OneToOne(fetch = FetchType.EAGER) // fetch the table with any explicit request by App
    @JoinColumn(name = "project_id",nullable = false) // create a column called "project_id" in Backlog table in DB. Also it can't be null.
    @JsonIgnore // prevent the infinite recursion call when relationship established with project
    private Project project;

    //OneToMany with projecttasks

}
