package boostSoft.boostTest.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commandId;
	private Date dateCreation;
	private float totalPrice;
	private Long customerPhone;
	private String status;

	@ManyToMany
	List<Product> products;

	@ManyToOne(cascade =  CascadeType.PERSIST, fetch =  FetchType.EAGER)
	CommandType commandtype;

	public Command() {
		super();
	}

	public Command(Long commandId, Date dateCreation, float totalPrice, Long customerPhone, String status,
			List<Product> products, CommandType commandtype) {
		super();
		this.commandId = commandId;
		this.dateCreation = dateCreation;
		this.totalPrice = totalPrice;
		this.customerPhone = customerPhone;
		this.status = status;
		this.products = products;
		this.commandtype = commandtype;
	}

	public Long getCommandId() {
		return commandId;
	}

	public void setCommandId(Long commandId) {
		this.commandId = commandId;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(Long customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public CommandType getCommandtype() {
		return commandtype;
	}

	public void setCommandtype(CommandType commandtype) {
		this.commandtype = commandtype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandId == null) ? 0 : commandId.hashCode());
		result = prime * result + ((commandtype == null) ? 0 : commandtype.hashCode());
		result = prime * result + ((customerPhone == null) ? 0 : customerPhone.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (commandId == null) {
			if (other.commandId != null)
				return false;
		} else if (!commandId.equals(other.commandId))
			return false;
		if (commandtype == null) {
			if (other.commandtype != null)
				return false;
		} else if (!commandtype.equals(other.commandtype))
			return false;
		if (customerPhone == null) {
			if (other.customerPhone != null)
				return false;
		} else if (!customerPhone.equals(other.customerPhone))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Command [commandId=" + commandId + ", dateCreation=" + dateCreation + ", totalPrice=" + totalPrice
				+ ", customerPhone=" + customerPhone + ", status=" + status + ", products=" + products
				+ ", commandtype=" + commandtype + "]";
	}

}
