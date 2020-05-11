package boostSoft.boostTest.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductCommand {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long IdCommandProduct;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Command command;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	private int quantityOrdered;
	private int AvailableQuantity;

	public ProductCommand(Long idCommandProduct, Command command, Product product,int a ,int b) {
		super();
		IdCommandProduct = idCommandProduct;
		this.command = command;
		this.product = product;
		this.quantityOrdered=a;
				this.AvailableQuantity=b;
	}

	public ProductCommand() {
		super();
	}

	public Long getIdCommandProduct() {
		return IdCommandProduct;
	}

	public void setIdCommandProduct(Long idCommandProduct) {
		IdCommandProduct = idCommandProduct;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getAvailableQuantity() {
		return AvailableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		AvailableQuantity = availableQuantity;
	}

	@Override
	public String toString() {
		return "ProductCommand [IdCommandProduct=" + IdCommandProduct + ", command=" + command + ", product=" + product
				+ "]";
	}

}
