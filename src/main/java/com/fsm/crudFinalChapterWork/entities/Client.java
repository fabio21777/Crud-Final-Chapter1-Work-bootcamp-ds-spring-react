package com.fsm.crudFinalChapterWork.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_client")
public class Client {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String name;
	private String cpf;
	private Double income;
	@Column(name = "bith_date")
	private Instant birthDate;
	private Integer children;

	public Client() {
	}

	public Client(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
