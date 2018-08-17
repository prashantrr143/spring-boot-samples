package in.prashant.microservices.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.prashant.microservices.model.UserRegistrationModel;
import in.prashant.microservices.service.StorageService;

@RestController
@RequestMapping("/api/v1/documents/")
public class UploadController {

	@Autowired
	private StorageService storageService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadDocument(@RequestParam("files") MultipartFile[] file,
			RedirectAttributes redirectAttributes) {
		System.out.println("SingleFiles Upload called");
		MultipartFile toUploadFile = file[0];
		if (toUploadFile.isEmpty()) {
			return new ResponseEntity<String>("Please select a valid file", HttpStatus.OK);
		}
		try {
			// save the file to the location
			storageService.store(toUploadFile);
		} catch (Exception ie) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Successfully Uploaded file - " + toUploadFile.getOriginalFilename(),
				new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/upload/multi")
	public ResponseEntity<String> uploadMultipleDocuments(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("emailId") String emailId,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes) {

		UserRegistrationModel user = new UserRegistrationModel();
		user.setEmailId(emailId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhoneNumber(phoneNumber);
		System.out.println(" Information About User " + user + " uploaded");

		String uploadedFilesNames = Arrays.stream(files).map(f -> f.getOriginalFilename())
				.filter(n -> n != null && n.trim().length() > 0).collect(Collectors.joining(","));
		System.out.println("Uploaded Filesd Names are are --> " + uploadedFilesNames);

		if (uploadedFilesNames.isEmpty()) {
			return new ResponseEntity<String>("Please select atleast a single file to upload", HttpStatus.OK);
		}
		try {
			// save the file to the location
			storageService.store(files);
		} catch (Exception ie) {
			return new ResponseEntity<String>(ie.getLocalizedMessage() , HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Successfully Uploaded file/files - " + uploadedFilesNames, new HttpHeaders(),
				HttpStatus.OK);
	}

	@PostMapping("/upload/multi/model")
	public ResponseEntity<String> uploadMultipleDocumentsByModel(@ModelAttribute UserRegistrationModel userRegModel,
			RedirectAttributes redirectAttributes) {
		System.out.println(" Information About User Model " + userRegModel + " uploaded");

		String uploadedFilesNames = Arrays.stream(userRegModel.getFiles()).map(f -> f.getOriginalFilename())
				.filter(n -> n != null && n.trim().length() > 0).collect(Collectors.joining(","));
		System.out.println("Uploaded Filesd Names are are --> " + uploadedFilesNames);

		if (uploadedFilesNames.isEmpty()) {
			return new ResponseEntity<String>("Please select atleast a single file to upload", HttpStatus.OK);
		}
		try {
			// save the file to the location
			storageService.store(userRegModel.getFiles());
		} catch (Exception ie) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Successfully Uploaded file/files - " + uploadedFilesNames, new HttpHeaders(),
				HttpStatus.OK);
	}

}
