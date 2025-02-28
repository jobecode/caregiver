package es.carapapa.caregiver.controller;

import es.carapapa.caregiver.model.User;
import es.carapapa.caregiver.service.UserService;
import es.carapapa.caregiver.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Optional<User> user = userService.findByUsername(authRequest.getUsername());
        if (user.isPresent() && userService.validatePassword(authRequest.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    @GetMapping("/listUsers")
    public ResponseEntity<String> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        if (authRequest.getPassword().length() < 6 || !authRequest.getPassword().matches(".*[a-zA-Z].*")) {
            return ResponseEntity.badRequest().body("La contraseña debe tener al menos 6 caracteres y una letra.");
        }
        if (userService.findByUsername(authRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe.");
        }
        userService.saveUser(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok("Usuario creado exitosamente");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        }
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> update(@RequestBody AuthRequest authRequest) {
        Optional<User> user = userService.findByUsername(authRequest.getUsername());
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            userService.saveUser(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        }
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }


}
