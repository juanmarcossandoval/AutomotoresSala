package com.Salas.Automotores.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class ModelBase {
	
	@CreationTimestamp
    private Timestamp createAt;
	
    @UpdateTimestamp
    private Timestamp updateAt;
    
}
