package com.yhkim.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", columnDefinition = "DATE")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at", columnDefinition = "DATE")
	private LocalDateTime deletedAt;
}


