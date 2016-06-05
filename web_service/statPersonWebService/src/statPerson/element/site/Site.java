package statPerson.element.site;

import java.util.Calendar;

public class Site{
    private int id;
    private String name;
    private Calendar StartDateStatistics;

    Site(){}

	public Site(String name, Calendar startDateStatistics) {
		super();
		this.name = name;
		StartDateStatistics = startDateStatistics;
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

	public Calendar getStartDateStatistics() {
		return StartDateStatistics;
	}

	public void setStartDateStatistics(Calendar startDateStatistics) {
		StartDateStatistics = startDateStatistics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StartDateStatistics == null) ? 0 : StartDateStatistics.hashCode());
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
		if (StartDateStatistics == null) {
			if (other.StartDateStatistics != null)
				return false;
		} else if (!StartDateStatistics.equals(other.StartDateStatistics))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
}
