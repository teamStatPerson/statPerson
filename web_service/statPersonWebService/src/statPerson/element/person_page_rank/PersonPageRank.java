package statPerson.element.person_page_rank;

public class PersonPageRank {
	private int id;
    private int personId;
    private int pageId;
    private int rank;
    
    PersonPageRank(){}

	public PersonPageRank(int personId, int pageId, int rank) {
		super();
		this.personId = personId;
		this.pageId = pageId;
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pageId;
		result = prime * result + personId;
		result = prime * result + rank;
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
		PersonPageRank other = (PersonPageRank) obj;
		if (pageId != other.pageId)
			return false;
		if (personId != other.personId)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}
    
}
