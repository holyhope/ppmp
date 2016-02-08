package fr.onlineCV.entities;

import java.util.Collection;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.onlineCV.tools.PasswordConvert;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Last name of the user.
	 */
	@NotNull(message = "{lastName.notNull}")
	private String last_name;

	/**
	 * First name of the user.
	 */
	@NotNull(message = "{firstName.notNull}")
	private String first_name;

	/**
	 * Email address of the user.
	 */
	@Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "{email.pattern}")
	@NotNull(message = "{email.notNull}")
	private String email;

	/**
	 * Hashed password of the user.
	 */
	@NotNull(message = "{password.notNull}")
	@Size(min = 5, message = "{password.minSize}")
	@Convert(converter = PasswordConvert.class)
	// @Converter( name = "passwordConverter", converterClass =
	// PasswordConvert.class )
	private String hash_password;

	/**
	 * Birth date of the user.
	 */
	@NotNull(message = "{birthday.notNull}")
	// @Pattern(regexp =
	// "(0[1-9]|1[0-9]|2[0-9]|3[01])\\/(0[1-9]|1[012])\\/[0-9]{4}", message =
	// "{birthday.pattern}")
	private java.util.Date birthdate;

	/**
	 * Path of the picture of the user localized in pictures directory.
	 */
	private String photo_path;

	/**
	 * Hobbies collection of the user.
	 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Hobby> hobbies;

	public Collection<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Collection<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHash_password() {
		return hash_password;
	}

	public void setHash_password(String hash_password) {
		this.hash_password = hash_password;
	}

	public java.util.Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(java.util.Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

}