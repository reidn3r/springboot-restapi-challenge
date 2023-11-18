package com.example.BossaBox.controller;

import com.example.BossaBox.DTO.AuthenticationDTO;
import com.example.BossaBox.DTO.LoginResponseDTO;
import com.example.BossaBox.DTO.RegisterDTO;
import com.example.BossaBox.domain.User.UserModel;
import com.example.BossaBox.infra.security.TokenService;
import com.example.BossaBox.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerController(@RequestBody @Valid RegisterDTO data){
    if(userRepo.findByUsername(data.username()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ja cadastrado.");

    BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    //Senha criptografada
    String hashPassword = pwEncoder.encode(data.password());

    //Entidade do novo registro
    UserModel newRegister = new UserModel(data.username(), hashPassword, data.role());

    //Salvamento do novo registro
    userRepo.save(newRegister);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginController(@RequestBody @Valid AuthenticationDTO data) {
        /* Comparação dos dados enviados no corpo da requisição com os dados salvos no BD */
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        /* Geração do token de autenticação */
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        /* Objeto resposta contendo o token */
        LoginResponseDTO response = new LoginResponseDTO(token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
