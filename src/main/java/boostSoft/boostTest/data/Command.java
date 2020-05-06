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
import javax.persistence.OneToMany;

@Entity
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commandId;
	private Date dateCreation;
	private float totalPrice;
	private Long customerPhone;
	private String status;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	CommandType commandtype;
	@OneToMany(mappedBy = "command", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<ProductCommand> productCommand;

	public Command(Long commandId, Date dateCreation, float totalPrice, Long customerPhone, String status,
			CommandType commandtype, List<ProductCommand> productCommand) {
		super();
		this.commandId = commandId;
		this.dateCreation = dateCreation;
		this.totalPrice = totalPrice;
		this.customerPhone = customerPhone;
		this.status = status;
		this.commandtype = commandtype;
		this.productCommand = productCommand;
	}

	public Command() {
		super();
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

	public CommandType getCommandtype() {
		return commandtype;
	}

	public void setCommandtype(CommandType commandtype) {
		this.commandtype = commandtype;
	}

	public List<ProductCommand> getProductCommand() {
		return productCommand;
	}

	public void setProductCommand(List<ProductCommand> productCommand) {
		this.productCommand = productCommand;
	}

	@Override
	public String toString() {
		return "Command [commandId=" + commandId + ", dateCreation=" + dateCreation + ", totalPrice=" + totalPrice
				+ ", customerPhone=" + customerPhone + ", status=" + status + ", commandtype=" + commandtype
				+ ", productCommand=" + productCommand + "]";
	}

}
