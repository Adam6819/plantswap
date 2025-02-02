package com.plantswap.plantswap.controllers;

import com.plantswap.plantswap.models.Plant;
import com.plantswap.plantswap.models.Transactions;
import com.plantswap.plantswap.models.User;
import com.plantswap.plantswap.repositories.PlantRepository;
import com.plantswap.plantswap.repositories.TransactionRespository;
import com.plantswap.plantswap.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/transactions")

public class TransactionsController{
    @Autowired
        private TransactionRespository transactionRespository;

    @Autowired
        private UserRepository userRepository;

    @Autowired
    private PlantRepository plantRepository;
// skapa en transaction
    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@Valid @RequestBody Transactions transactions){
        Transactions savedTransaction = transactionRespository.save(transactions);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);

    }
    // hämta alla transaction
    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        List<Transactions> transactions = transactionRespository.findAll();
        return ResponseEntity.ok(transactions);

    }
// hämta atransaction genom id
    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable String id){
        Transactions transactions = transactionRespository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction not found"));
        return ResponseEntity.ok(transactions);
    }
// ändra en transaction genom id
    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable String id, @RequestBody Transactions transactionDeitails){
        Transactions exitingTransaction = transactionRespository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found"));

        transactionDeitails.setAmount(transactionDeitails.getAmount());
        transactionDeitails.setPayment(transactionDeitails.getPayment());

        return ResponseEntity.ok(transactionRespository.save(exitingTransaction));
    }
// uppdatera en specifik transaction genom id
    @PatchMapping("/{id}")
    public ResponseEntity<Transactions> updateField(@PathVariable String userId, String transactionId, @RequestBody String plantId, boolean amount) {
       //här kollar vi om rätt user finns genom id
        User user = userRepository.findById(userId)
                // om rätt user inte finns genom id så kastar vi ett fel.
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        //här kollar vi om rätt plant finns genom id
        Plant plant = plantRepository.findById(plantId)
                // om rätt plant inte finns genom id så kastar vi ett fel
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        // hämta även transaction genom id och kolla att den finns
        Transactions transaction = transactionRespository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        // sätter status som boolean: true = tillgänglig. false = såld eller bytt

        plant.setStatus(false);

        // Här ska setters och getter finnas för att inte köra över data. Men jag får inte till det.
        plantRepository.save(plant);

        // men vi sätter värdet på amount till värdet vi får i request bodyn
        transaction.setAmount(amount);
        // Här ska setters och getter finnas för att inte köra över data. Men jag får inte till det.
        transactionRespository.save(transaction);

        return ResponseEntity.ok(transaction);
    }




    }






