package spring.Loginpage.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
GET http://YOUR_SERVER_IP:8080/api/oauth2/success **/


@RestController
public class OAuthController {

    @GetMapping("/api/oauth2/success")
    public ResponseEntity<?> oauth2Success(Authentication authentication) {
        if (authentication != null) {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ADMIN"));

            if (isAdmin) {
                return ResponseEntity.ok(Map.of(
                        "message", "OAuth login successful",
                        "role", "ADMIN",
                        "redirectUrl", "/admin/dashboard"
                ));
            }
        }

        return ResponseEntity.ok(Map.of(
                "message", "OAuth login successful",
                "role", "USER",
                "redirectUrl", "/user/dashboard"
        ));
    }
}
