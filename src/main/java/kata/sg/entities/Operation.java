package kata.sg.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import kata.sg.enums.OperationType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Amount is mandatory")
	private Double amount;

	private LocalDateTime date;
	
	@NotNull(message = "Operation type is mandatory")
	@Enumerated(EnumType.STRING)
	private OperationType type;

	@NotNull(message = "Bank account is mandatory")
	@ManyToOne
	private BankAccount bankAccount;

	public Operation() {
		this.date = LocalDateTime.now();
	}

	public Operation(OperationType type, Double amount) {
		this.type = type;
		this.amount = amount;
		this.date = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		return "Operation{"+
				"id=" + id +
				", type=" + type +
				", amount="+ amount +
				", date=" + date.format(formatter) + "}";
	}
}
