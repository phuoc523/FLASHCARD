//package com.flashcard.service;
//
//import com.flashcard.dao.UserDAO;
//import com.flashcard.model.User;
//
//import java.util.Optional;
//
//public class AuthService {
//
//    private static AuthService instance;
//    private final UserDAO userDAO = new UserDAO();
//    private User currentUser;
//
//    private AuthService() {}
//
//    public static AuthService getInstance() {
//        if (instance == null) instance = new AuthService();
//        return instance;
//    }
//
//    public boolean login(String input, String password) {
//        Optional<User> opt = userDAO.findByUserName(input);
//
//        if (opt.isEmpty()) {
//            opt = userDAO.findByEmail(input);
//        }
//
//        if (opt.isPresent()) {
//            User user = opt.get();
//            if (user.getPassword().equals(password)) {
//                currentUser = user;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public String register(String userName, String email,
//                           String password, String confirm) {
//
//        if (userName.isBlank()) return "Tên đăng nhập không được để trống";
//        if (userName.length() < 3)  return "Tên đăng nhập ít nhất 3 ký tự";
//        if (email.isBlank())    return "Email không được để trống";
//        if (!email.contains("@"))   return "Email không đúng định dạng";
//        if (password.length() < 6)  return "Mật khẩu ít nhất 6 ký tự";
//        if (!password.equals(confirm))  return "Mật khẩu không trùng khớp";
//
//        if (userDAO.existsByUserName(userName)) return "Tên đăng nhập đã tồn tại";
//        if (userDAO.existsByEmail(email))   return "Email đã được sử dụng";
//
//        User user = new User(userName, email, password);
//        return userDAO.save(user) ? null : "Lỗi đăng ký, vui lòng thử lại";
//    }
//
//    public User getCurrentUser()    { return currentUser; }
//    public void logout()    { currentUser = null; }
//    public boolean isLoggedIn() { return currentUser != null; }
//}


package com.flashcard.service;

import com.flashcard.dao.UserDAO;
import com.flashcard.model.User;

import java.util.Optional;

public class AuthService {

    private static AuthService instance;
    private final UserDAO userDAO = new UserDAO();
    private User currentUser;

    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) instance = new AuthService();
        return instance;
    }

    public boolean login(String input, String password) {

        Optional<User> opt = userDAO.findByUserName(input);

        if (opt.isEmpty()) {
            opt = userDAO.findByEmail(input);
        }

        if (opt.isPresent()) {
            User user = opt.get();

            if (user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public String register(String userName, String email,
                           String password, String confirm) {

        if (userName.isBlank()) return "Tên đăng nhập không được để trống";
        if (userName.length() < 3) return "Tên đăng nhập ít nhất 3 ký tự";
        if (email.isBlank()) return "Email không được để trống";
        if (!email.contains("@")) return "Email không hợp lệ";
        if (password.length() < 6) return "Mật khẩu ít nhất 6 ký tự";
        if (!password.equals(confirm)) return "Mật khẩu không trùng";

        if (userDAO.existsByUserName(userName)) return "Tên đăng nhập đã tồn tại";
        if (userDAO.existsByEmail(email)) return "Email đã tồn tại";

        User user = new User(userName, email, password);

        return userDAO.save(user) ? null : "Lỗi đăng ký";
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}