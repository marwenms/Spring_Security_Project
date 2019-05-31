package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ROLE")
@Entity
public class Role {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROLE")
	private Long id;
	@Column(name="Role")
	private String nomRole;
	public Role(Long id, String nomRole) {
		super();
		this.id = id;
		this.nomRole = nomRole;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomRole() {
		return nomRole;
	}
	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
