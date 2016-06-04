package statPerson.element.price;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class Price implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int maxAmountUsers;
	private int maxAmountSites;
	private int durationOfPriceDay;
	
	public Price(){}

	public Price(String name, int maxAmountUsers, int maxAmountSites, int durationOfPriceDay) {
		super();
		this.name = name;
		this.maxAmountUsers = maxAmountUsers;
		this.maxAmountSites = maxAmountSites;
		this.durationOfPriceDay = durationOfPriceDay;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
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

	public int getDurationOfPriceDay() {
		return durationOfPriceDay;
	}

	@XmlElement
	public void setDurationOfPriceDay(int durationOfPriceDay) {
		this.durationOfPriceDay = durationOfPriceDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + durationOfPriceDay;
		result = prime * result + maxAmountSites;
		result = prime * result + maxAmountUsers;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Price other = (Price) obj;
		if (durationOfPriceDay != other.durationOfPriceDay)
			return false;
		if (maxAmountSites != other.maxAmountSites)
			return false;
		if (maxAmountUsers != other.maxAmountUsers)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
