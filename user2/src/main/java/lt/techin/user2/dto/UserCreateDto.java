package lt.techin.user2.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class UserCreateDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int birthYear;

    public UserCreateDto() {}

    @Autowired
    public UserCreateDto(String username, String firstName, String lastName, String email, int birthYear) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthYear = birthYear;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
