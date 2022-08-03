package com.project.hm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="reg_hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRegistration{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	@NotBlank(message="This field should not be blank")
	private String firstName;
    @NotBlank(message="This field should not be blank")
	private String lastName;
    @NotBlank(message="This field should not be blank")
	private String userName;
    @Pattern(regexp="^[a-zA-Z0-9]{9}",message="length must be 8")  
    private String password;
	@Email
	private String email;
	@Min(value=18, message="must be equal or greater than 18")  
	private int age;
	//@Pattern(regexp = "[89][0-9]{10}", message = "Invalid mobile number entered")
   	private String phoneNo;
    private String role;
	

}
