package elements;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class Price implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private int maxAmountUsers;
	private int maxAmountSites;
	private Date durationOfPrice;
	
	public Price(){}

	public Price(int id, int maxAmountUsers, int maxAmountSites, Date durationOfPrice) {
		super();
		this.id = id;
		this.maxAmountUsers = maxAmountUsers;
		this.maxAmountSites = maxAmountSites;
		this.durationOfPrice = durationOfPrice;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public int getMaxAmountUsers() {
		return maxAmountUsers;
	}

	@XmlElement
	public void setMaxAmountUsers(int maxAmountUsers) {
		this.maxAmountUsers = maxAmountUsers;
	}

	public int getMaxAmountSites() {
		return maxAmountSites;
	}

	@XmlElement
	public void setMaxAmountSites(int maxAmountSites) {
		this.maxAmountSites = maxAmountSites;
	}

	public Date getDurationOfPrice() {
		return durationOfPrice;
	}

	@XmlElement
	public void setDurationOfPrice(Date durationOfPrice) {
		this.durationOfPrice = durationOfPrice;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (durationOfPrice == null) {
			if (other.durationOfPrice != null)
				return false;
		} else if (!durationOfPrice.equals(other.durationOfPrice))
			return false;
		if (id != other.id)
			return false;
		if (maxAmountSites != other.maxAmountSites)
			return false;
		if (maxAmountUsers != other.maxAmountUsers)
			return false;
		return true;
	}
	
}
