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

}
