package in.prashant.microservices.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	
	private  String locationPath;
	
	@Autowired
	private Environment env;

	public StorageServiceImpl() {
		super();
		 init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		// locationPath = env.getProperty("microservice.file.upload.temp.location");
		if(env != null ) {
		locationPath = env.getProperty("microservice.file.upload.temp.location");
		}
		System.out.println(" Final Destination for file upload is  " + locationPath);
		System.out.println(" Files Upload Location : " + locationPath);
		if (locationPath != null) {
			Path path = Paths.get(locationPath);
			if (!Files.exists(path)) {
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private void writeFile(MultipartFile file) {
		System.out.println(" Writing started for : " + file.getOriginalFilename());
		Path path = Paths.get(locationPath, file.getOriginalFilename());
		
		
		try {
			Files.createFile(path);
			Files.write(path, file.getBytes(), StandardOpenOption.WRITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(" Writing Done  for : " + file.getOriginalFilename());
	}

	@Override
	public void store(MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println(" Store Method Called for " + file.getOriginalFilename());
		System.out.println(" Total Size of File uploaded is " + file.getSize());
		writeFile(file);

	}

	@Override
	public void store(MultipartFile[] files) {

		System.out.println(" Total Size of Files uploaded is " + files.length);
		Arrays.stream(files).forEach(f -> writeFile(f));
	//	throw new RuntimeException("Added for Checking");

	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
