package statPerson.element.administrator_price;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "administratorPrice")
public class AdministratorPrice implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int idAdministrator;
	private int idPrice;
	private Date dateOfPay;

	public AdministratorPrice() {
	}

	public AdministratorPrice(int idAdministrator, int idPrice, Date dateOfPay) {
		super();
		this.idAdministrator = idAdministrator;
		this.idPrice = idPrice;
		this.dateOfPay = dateOfPay;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public int getIdAdministrator() {
		return idAdministrator;
	}

	@XmlElement
	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}

	public int getIdPrice() {
		return idPrice;
	}

	@XmlElement
	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
	}

	public Date getDateOfPay() {
		return dateOfPay;
	}

	@XmlElement
	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministratorPrice other = (AdministratorPrice) obj;
		if (dateOfPay == null) {
			if (other.dateOfPay != null)
				return false;
		} else if (!dateOfPay.equals(other.dateOfPay))
			return false;
		if (idAdministrator != other.idAdministrator)
			return false;
		if (idPrice != other.idPrice)
			return false;
		return true;
	}
}
