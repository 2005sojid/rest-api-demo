package com.example.restapidemo.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updatedDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
