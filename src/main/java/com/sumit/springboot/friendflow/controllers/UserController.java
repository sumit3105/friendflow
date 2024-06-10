package com.sumit.springboot.friendflow.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sumit.springboot.friendflow.entities.Friendship;
import com.sumit.springboot.friendflow.entities.Image;
import com.sumit.springboot.friendflow.entities.Post;
import com.sumit.springboot.friendflow.entities.User;
import com.sumit.springboot.friendflow.service.FriendshipService;
import com.sumit.springboot.friendflow.service.ImageService;
import com.sumit.springboot.friendflow.service.PostService;
import com.sumit.springboot.friendflow.service.UserService;
import com.sumit.springboot.friendflow.session.UserSessionManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FriendshipService friendService;
    @Autowired
    private PostService postService;

    private static final String UPLOADED_FOLDER = "src/main/resources/static/img/uploads/";

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login/login";
    }

    @GetMapping("/")
    public String showIndexPage(Model model, HttpSession session) {
    	User user = UserSessionManager.getLoggedInUser(session);
    	if(user != null) {
    		return "redirect:/user/home";    		
    	}
    	else {
    		return "redirect:/login";
    	}
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password,
                        RedirectAttributes redirectAttributes, Model model) {
        if (userService.authenticateUser(username, password)) {
            User user = userService.getUserByUsername(username);
            UserSessionManager.setUserLoggedIn(session, user);
            return "redirect:/user/home";
            
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        UserSessionManager.logoutUser(session);
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "login/signup";
    }

    public static Image generateImage(MultipartFile profileImage) {
        try {
            File uploadDir = new File(UPLOADED_FOLDER);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            byte[] bytes = profileImage.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + profileImage.getOriginalFilename());
            Files.write(path, bytes);

            Image image = new Image();
            image.setName(profileImage.getOriginalFilename());
            image.setImageUrl("/img/uploads/" + profileImage.getOriginalFilename());

            return image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                         @RequestParam("profileDetails") String profileDetails,
                         @RequestParam("profileImage") MultipartFile profileImage, Model model, RedirectAttributes redirectAttributes) {

    	User u = userService.getUserByUsername(username);
    	
    	if(u != null) {
    		redirectAttributes.addFlashAttribute("error", true);
    		return "redirect:/signup";
    	}

        Image image = generateImage(profileImage);

        User user = new User(username, password, firstName, lastName, profileDetails, null, image);
        userService.createUser(user);

        return "redirect:/login";
    }

    @GetMapping("/user/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = UserSessionManager.getLoggedInUser(session);
//        Hibernate.initialize(user.getPosts());
        model.addAttribute("user", user);
//        System.out.println("--------------------Posts from user controller--------------------");
//        System.out.println(user.getPosts());
        
        List<Friendship> pending = friendService.getPending(user);
        model.addAttribute("pending", pending);
		return "profile";
    }

    @PostMapping("/user/update")
    public String updateProfile(@RequestParam("username") String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "updateForm";
    }

    @PostMapping("/user/save")
    public String saveProfile(@RequestParam("username") String username, @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName, @RequestParam("profileDetails") String profileDetails,
                              @RequestParam("profileImage") MultipartFile profileImage, Model model, HttpSession session) {

        User user = userService.getUserByUsername(username);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setProfileDetails(profileDetails);

        if (!profileImage.isEmpty()) {
            Image image = generateImage(profileImage);
            Image old = user.getProfileImage();
            imageService.deleteImage(old);
            user.setProfileImage(image);
        }

        userService.updateUser(user);
        UserSessionManager.setUserLoggedIn(session, user);
        return "redirect:/user/home";
    }
    
    @GetMapping("/user/home")
    public String viewHome(HttpSession session, Model model) {
    	User user = UserSessionManager.getLoggedInUser(session);
    	model.addAttribute("user", user);
    	
    	List<User> all = userService.getAllUserExcept(user);
        model.addAttribute("allUser", all);
   
        List<Friendship> pending = friendService.getPending(user);
        model.addAttribute("pending", pending);
        
        List<Post> posts = postService.getPostsOfFriends(user);
        model.addAttribute("posts", posts);
        
        for(Friendship f : pending) {
        	System.out.println(f);        	
        }
        
        for(User u : all) {
        	System.out.println(u);
        }
        
    	return "index";
    }
}
