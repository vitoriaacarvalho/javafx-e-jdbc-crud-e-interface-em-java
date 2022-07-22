package model.entities;

import java.util.Objects;

public class Department {
	private Integer id;
	private String Name;
	
	public Department() {
	}
	
	public Department(Integer id, String name) {
		this.id = id;
		Name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Name, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(Name, other.Name) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", Name=" + Name + "]";
	}

	

}
