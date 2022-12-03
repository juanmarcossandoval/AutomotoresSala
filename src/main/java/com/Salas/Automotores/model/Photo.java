package com.Salas.Automotores.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Photos")
public class Photo extends ModelBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id", unique = true, nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicleId;
}
