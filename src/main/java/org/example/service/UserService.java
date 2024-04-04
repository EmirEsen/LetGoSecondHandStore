package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.util.Util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class UserService {

    UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void register() {
        String username;
        String password;
        boolean usernameValid;
        boolean passwordValid;

        do {
            username = Util.stringScanner("Username: ");
            usernameValid = userRepository.findByColumnAndValue("username", username).isEmpty();
            if (!usernameValid) {
                System.out.printf("Username %s already exists!\n", username);
            }
        } while (!usernameValid);

        do {
            password = Util.stringScanner("Password: ");
            passwordValid = userRepository.findByColumnAndValue("password", password).isEmpty();
            if (!passwordValid) {
                System.out.println("Enter a different password");
            }
        } while (!passwordValid);


        String email;
        do {
            email = Util.stringScanner("Email: ");
            if (!isEmailValid(email)) {
                System.out.println("Please enter a valid email!");
            }
        } while (!isEmailValid(email));

        String phone = Util.stringScanner("Phone: ");


        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .build();

        userRepository.save(user);

    }

    private boolean isEmailValid(String email) {
        String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }


    public User login() {
        String username;
        String password;
        User userFound = null;
        boolean validPass;
        do {
            username = Util.stringScanner("Username: ");
            try {
                userFound = userRepository.findByColumnAndValue("username", username.trim()).getFirst();
            } catch (NoSuchElementException e) {
                System.out.printf("%s not found.\n", username);
            }

        } while (userFound == null);

        do {
            password = Util.stringScanner("Password: ");
            validPass = userFound.getPassword().equals(password);
            if (!validPass) {
                System.out.println("Wrong Password, Try Again");
            }
        } while (!validPass);

        return userFound;
    }
}
