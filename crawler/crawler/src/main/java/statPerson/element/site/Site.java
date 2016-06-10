package statPerson.element.site;

import java.util.Date;

public class Site{
    private int id;
    private String name;
    private Date startDateStatistics;

    public Site(){}

	public Site(String name, Date startDateStatistics) {
		super();
		this.name = name;
		this.startDateStatistics = startDateStatistics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDateStatistics() {
		return startDateStatistics;
	}

	public void setStartDateStatistics(Date startDateStatistics) {
		this.startDateStatistics = startDateStatistics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startDateStatistics == null) ? 0 : startDateStatistics.hashCode());
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
		Site other = (Site) obj;
		if (startDateStatistics == null) {
			if (other.startDateStatistics != null)
				return false;
		} else if (!startDateStatistics.equals(other.startDateStatistics))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
}
