package statPerson.elements;

import java.util.Calendar;

public class Sites{

    private int id;
    private String name;
    private Calendar StartDateStatistics;

    public Sites(){}
    public Sites(String name, Calendar startDateStatistics) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sites sites = (Sites) o;

        if (id != sites.id) return false;
        if (name != null ? !name.equals(sites.name) : sites.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
