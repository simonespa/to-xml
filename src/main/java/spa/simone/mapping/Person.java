package spa.simone.mapping;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Simone Spaccarotella {spa.simone@gmail.com}
 */
@XmlRootElement(name="person")
@XmlType(propOrder = { "firstName", "lastName", "birthDate" })
public class Person {

	private String firstName;
	private String lastName;
	private Date birthDate;

	@XmlElement(name = "first-name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name = "last-name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "birth-date")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
