package beans;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;

	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String gender;
	private String description;
	private String password;
	private String salt;
	private String image;
	private String dob;

	public UserBean() {

	}

	public UserBean(int userId, String firstName, String lastName, String userName, String email, String gender,
			String description, String password, String salt, String image, String dob) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.gender = gender;
		this.description = description;
		this.password = password;
		this.salt = salt;
		this.image = image;
		this.dob = dob;
	}

	public int getUserId() {
		return userId;

	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		firstName = resolveNull(firstName);
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		lastName = resolveNull(lastName);
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		userName = resolveNull(userName);
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		email = resolveNull(email);
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		gender = resolveNull(gender);
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescription() {
		description = resolveNull(description);
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		password = resolveNull(password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getImage() {
		image = resolveNull(image);
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDob() {
		dob = resolveNull(dob);
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	private String resolveNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}
}
