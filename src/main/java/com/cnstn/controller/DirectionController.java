package com.cnstn.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnstn.entities.Direction;
import com.cnstn.service.BackServiceDirection;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("dir")
public class DirectionController {
	@Autowired
	BackServiceDirection backservice;
	@Autowired
    private HttpServletRequest request;
	@PostMapping
	public Direction createDir(@RequestBody Direction direction){
        Direction savedDir = backservice.addDirections(direction);
        String ipAddress = request.getHeader("X-Forwarded-For");
	    String hostName = null ;
		String userAgent = null;
		 Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateTime = dateFormat.format(date);
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	         hostName = request.getRemoteHost();
	         userAgent = request.getHeader("User-Agent");	        
	    }
	    try {
	        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
	        writer.write("ajouté une direction :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return savedDir;
    }	
	@GetMapping("directions")
	 public List<Direction> getAllDir(){
	        List<Direction> directions = backservice.AfficherListDir();
	        return directions;
	    }
	@GetMapping("{id}")
	    public Direction LoadDirectionById(@PathVariable("id") Long id){
	        Direction direction = backservice.LoadDirectionById(id);
	        return direction;
	    }
	@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteDirection(@PathVariable("id") Long id){
        backservice.deleteDirection(id);
        String ipAddress = request.getHeader("X-Forwarded-For");
	    String hostName = null ;
		String userAgent = null;
		 Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateTime = dateFormat.format(date);
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	         hostName = request.getRemoteHost();
	         userAgent = request.getHeader("User-Agent");	        
	    }
	    try {
	        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
	        writer.write("effacer une direction :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return ResponseEntity.noContent().build();
    }
	@PutMapping("{id}")
    public ResponseEntity<Direction> updateDirection(@PathVariable("id") Long id,
                                           @RequestBody Direction direction){
        direction.setId(id);
        Direction updatedDirection = backservice.updateDirection(direction);
        return new ResponseEntity<>(updatedDirection, HttpStatus.OK);
    }
	 @PutMapping("/directions/{id}")
	 public ResponseEntity<Direction> updateProduct(@PathVariable Long id, @RequestBody Direction updatedProduct) {
		 Direction direction=backservice.loadDirections(id); 
		 direction.setNom(updatedProduct.getNom());
		 direction.setDescription(updatedProduct.getDescription());
		 Direction directionmaj= backservice.addDirections(direction);
		 String ipAddress = request.getHeader("X-Forwarded-For");
		    String hostName = null ;
			String userAgent = null;
			 Date date = new Date();
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String dateTime = dateFormat.format(date);
			if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
		        ipAddress = request.getRemoteAddr();
		         hostName = request.getRemoteHost();
		         userAgent = request.getHeader("User-Agent");		        
		    }
		    try {
		        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
		        writer.write("mise a jour une direction :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
	    }
}
