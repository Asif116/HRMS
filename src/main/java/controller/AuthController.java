package spring.Loginpage.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.Loginpage.DTO.AuthDTO;
import spring.Loginpage.Service.EmailService;
import spring.Loginpage.Service.UserServiceImpl;
import spring.Loginpage.Model.User;

import java.util.Map;


/**
 1. Login
POST http://YOUR_SERVER_IP:8080/api/auth/login
Body (JSON):
        {
        "email": "user@example.com",
        "password": "123456"
        }

        2. Register
POST http://YOUR_SERVER_IP:8080/api/auth/register
Body (JSON):
        {
        "name": "John Doe",
        "email": "john@example.com",
        "password": "123456"
        }

        3. Verify OTP
POST http://YOUR_SERVER_IP:8080/api/auth/verifyOtp?enteredOtp=123456

        4. Logout
POST http://YOUR_SERVER_IP:8080/api/auth/logout

        5. Forgot Password
POST http://YOUR_SERVER_IP:8080/api/auth/forgotPassword?email=user@example.com

        6. Reset Password
POST http://YOUR_SERVER_IP:8080/api/auth/resetPassword?token=RESET_TOKEN&newPassword=123456

        7. Dashboard
GET http://YOUR_SERVER_IP:8080/api/auth/dashboard


        ✅ Replace YOUR_SERVER_IP with your backend IP or localhost (10.0.2.2 for Android Emulator).**/



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow all origins for React Native frontend
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailService emailService;

    /** LOGIN **/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO, HttpSession session) {
        try {
            User user = userService.validateLogin(authDTO.getEmail(), authDTO.getPassword());
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid email or password"));
            }

            session.setAttribute("user", user);
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "role", user.getRole(),
                    "userId", user.getId(),
                    "email", user.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    /** REGISTER **/
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user, HttpSession session) {
        try {
            if (userService.isEmailAlreadyRegistered(user.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email already registered"));
            }

            String otp = emailService.generateOtp();
            emailService.sendOtpEmail(user.getEmail(), otp);

            session.setAttribute("tempUser", user);
            session.setAttribute("otp", otp);
            session.setAttribute("otpTime", System.currentTimeMillis());

            return ResponseEntity.ok(Map.of(
                    "message", "OTP sent to email successfully",
                    "email", user.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    /** VERIFY OTP **/
    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestParam("enteredOtp") String enteredOtp, HttpSession session) {
        String storedOtp = (String) session.getAttribute("otp");
        Long otpTime = (Long) session.getAttribute("otpTime");

        if (storedOtp == null || otpTime == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Session expired. Please register again."));
        }

        if (System.currentTimeMillis() - otpTime > 5 * 60 * 1000) {
            session.invalidate();
            return ResponseEntity.badRequest().body(Map.of("error", "OTP expired. Please register again."));
        }

        if (storedOtp.equals(enteredOtp)) {
            User tempUser = (User) session.getAttribute("tempUser");

            if (tempUser != null) {
                userService.saveUser(tempUser);
                session.removeAttribute("otp");
                session.removeAttribute("otpTime");
                session.removeAttribute("tempUser");
                return ResponseEntity.ok(Map.of("message", "Registration successful!"));
            }
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Invalid OTP"));
    }

    /** LOGOUT **/
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    /** FORGOT PASSWORD **/
    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        try {
            userService.processForgotPassword(email);
            return ResponseEntity.ok(Map.of("message", "Password reset link sent to your email"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /** RESET PASSWORD **/
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(
            @RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword
    ) {
        try {
            userService.updatePassword(token, newPassword);
            return ResponseEntity.ok(Map.of("message", "Password reset successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /** GET DASHBOARD (role-based) **/
    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.ok(Map.of("message", "Welcome to Admin Dashboard"));
        } else {
            return ResponseEntity.ok(Map.of("message", "Welcome to User Dashboard"));
        }
    }
}
