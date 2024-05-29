package com.sumit.springboot.friendflow.session;

import com.sumit.springboot.friendflow.entities.User;
import jakarta.servlet.http.HttpSession;

public class UserSessionManager {
    public static final String USER_SESSION_ATTRIBUTE = "user";

    public static void setUserLoggedIn(HttpSession session, User user) {
        session.setAttribute(USER_SESSION_ATTRIBUTE, user);
    }

    public static void logoutUser(HttpSession session) {
        session.removeAttribute(USER_SESSION_ATTRIBUTE);
        session.invalidate();
    }

    public static User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_ATTRIBUTE);
    }
    
    public static boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute(USER_SESSION_ATTRIBUTE) != null;
    }
}
