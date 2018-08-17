package in.prashant.microservices.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Model class to contain both the form fields and the file Attributes
 * 
 * @author prashantsingh
 * 
 *
 */
public class UserRegistrationModel {

	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;

	private MultipartFile[] files;

	public String getFirstName() {
		return firstName;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lastName;
	}

	public void setLastName(String lasttName) {
		this.lastName = lasttName;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "UserRegistrationModel [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
