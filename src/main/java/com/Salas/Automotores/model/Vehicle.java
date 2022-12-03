package com.Salas.Automotores.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vehicles")
public class Vehicle extends ModelBase{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "kilometers")
	private Integer kilometers;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "gasType")
	private String gasType; 
	
	@Column(name = "color")
	private String color;
	
	@Column(name= "price")
	private Integer price;
	
	@Column(name= "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brandId;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner ownerId;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, mappedBy = "vehicleId")
	private List<Photo> photos = new ArrayList<>();
}
