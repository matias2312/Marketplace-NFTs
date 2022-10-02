package com.example.market.controllers;

import com.example.market.DTO.ClientDTO;
import com.example.market.Services.ClientService;
import com.example.market.Services.ImplementationServices.PDFServiceImpl;
import com.example.market.Services.UserService;
import com.example.market.emails.ValidatorEmail;
import com.example.market.emails.VerificationEmail;
import com.example.market.models.Client;
import com.example.market.models.ClientProduct;
import com.example.market.models.Sale;
import com.example.market.repositories.ClientRepository;
import com.example.market.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final SaleRepository saleRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationEmail verificationEmail;
    private final ValidatorEmail validatorEmail;
    private final PDFServiceImpl pdfServiceImpl;
    private final UserService userService;

    @GetMapping("/clients")
    public Set<ClientDTO> getAllClients() {
        return  clientService.getClients().stream().map(client -> new ClientDTO(client)).collect(Collectors.toSet());
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return new ClientDTO(clientService.getClientById(id));
    }
    @GetMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication auth) {
        try {
            return new ClientDTO(clientService.findByClientEmail(auth.getName()));
        } catch (NullPointerException nullPointerException) {
            return null;
        }
    }

    @PostMapping("/clients/register")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByClientEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        if (!validatorEmail.test(email)) {
            return new ResponseEntity<>("invalid mail", HttpStatus.FORBIDDEN);
        }

        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password), 1000.00, true, false);
        clientService.saveClient(newClient);

        String mailSubject = "Artics Verification Email";
        String mailBody = "Hey! We are so close to complete your account registration, just one more thing to do. We need you to go to this link: http://localhost:8080/api/clients/verify/"
                + newClient.getId();

        verificationEmail.sendEmail(newClient.getEmail(), mailSubject, mailBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients/verify/{id}")
    public RedirectView verifyClient(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        client.setVerified(true);
        clientService.saveClient(client);
       // return new RedirectView("http://localhost:8080/web/Structure/register.html");
        return new RedirectView("https://ecommers-nfts.herokuapp.com/web/Structure/register.html");
    }

    @PostMapping("/clients/current/endPurchase")
    public ResponseEntity<String> endPurchase(Authentication auth, HttpServletResponse response) throws IOException {
        Client user = clientService.findByClientEmail(auth.getName());

        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        Set<ClientProduct> shoppingBag = user.getClientProducts();
        Sale sale = new Sale(user);
        saleRepository.save(sale);
        ByteArrayOutputStream outPutStream = pdfServiceImpl.generatePDF(response, user, shoppingBag);
        byte[] bytes = outPutStream.toByteArray();

        if (userService.sendInvoice(user, bytes)) {
            userService.clearCart(user);
            return new ResponseEntity<String>("Invoice succesfully sent", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Something wrong happened", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/clients/current/sendInvoice")
    public ResponseEntity<String> sendInvoice(Authentication auth, HttpServletResponse response) throws IOException {
        Client user = clientService.findByClientEmail(auth.getName());
        Set<ClientProduct> shoppingBag = user.getClientProducts();

        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        ByteArrayOutputStream outPutStream = pdfServiceImpl.generatePDF(response, user, shoppingBag);
        byte[] bytes = outPutStream.toByteArray();
        if (userService.sendInvoice(user, bytes)) {
            return new ResponseEntity<String>("Invoice succesfully sent", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Something wrong happened", HttpStatus.BAD_REQUEST);
    }
}
