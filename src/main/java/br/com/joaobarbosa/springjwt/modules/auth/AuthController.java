package br.com.joaobarbosa.springjwt.modules.auth;

import br.com.joaobarbosa.springjwt.modules.auth.dto.LoginResquest;
import br.com.joaobarbosa.springjwt.modules.auth.dto.RegisterResquest;
import br.com.joaobarbosa.springjwt.modules.users.User;
import br.com.joaobarbosa.springjwt.modules.users.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginResquest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = jwtService.generateToken(auth.getName());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterResquest data) {
        if (this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.username(), encryptedPassword);
        this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
