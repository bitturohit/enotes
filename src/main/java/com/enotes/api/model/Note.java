package com.enotes.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private boolean isArchived = false;

	private boolean deleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@PrePersist
	protected void onCreate()
	{
		this.createdAt = this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate()
	{
		this.updatedAt = LocalDateTime.now();
	}
}
