package statPerson.element.keyword;

public class Keyword{

	private int id;
	private String name;
	private int personId;

	Keyword() {
	}

	public Keyword(String name, int personId) {
		super();
		this.name = name;
		this.personId = personId;
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

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Keyword keywords = (Keyword) o;

		if (id != keywords.id)
			return false;
		if (personId != keywords.personId)
			return false;
		if (name != null ? !name.equals(keywords.name) : keywords.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + personId;
		return result;
	}
}
