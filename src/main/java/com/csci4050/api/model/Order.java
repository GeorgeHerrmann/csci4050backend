package com.csci4050.api.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ticket_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	Payment payment;
	
	@Column(name = "user_id")
	Long userId;
	
	@Column(name = "promotion_id")
	Long promotionId;
	
	@ManyToOne
	@JoinColumn(name = "show_id", referencedColumnName = "id")
	Show show;
	
	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
	List<Ticket> tickets;
	
	@Column(name = "subtotal")
	Float subtotal;
	
	@Column(name = "total")
	Float total;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "created_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp created;

}
