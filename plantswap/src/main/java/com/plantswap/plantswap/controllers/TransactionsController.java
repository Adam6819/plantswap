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

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@Valid @RequestBody Transactions transactions){
        Transactions savedTransaction = transactionRespository.save(transactions);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);


    }
    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        List<Transactions> transactions = transactionRespository.findAll();
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable String id){
        Transactions transactions = transactionRespository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction not found"));
        return ResponseEntity.ok(transactions);
    }
// ska det vara endast id här eller ska jag lägga EAN nummer här också?
    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable String id, @RequestBody Transactions transactionDeitails){
        Transactions exitingTransaction = transactionRespository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found"));

        transactionDeitails.setAmount(transactionDeitails.getAmount());
        transactionDeitails.setPayment(transactionDeitails.getPayment());

        return ResponseEntity.ok(transactionRespository.save(exitingTransaction));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Transactions> updatefield(@PathVariable String userId, String transactionId, @RequestBody String plantId, boolean amount) {
       //här kollar vi om rätt user finns genom id
        User user = userRepository.findById(userId)
                // om rätt user inte finns genom id så kastar vi ett fel.
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        //här kollar vi om rätt plant finns genom id
        Plant plant = plantRepository.findById(plantId)
                // om rätt plant inte finns genom id så kastar vi ett fel
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        // hämta även transaction och kolla att den finns
        Transactions transaction = transactionRespository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        // om du ändrar status till boolean istället för String på Plant så kan du ha true om det är
        // tillgänglig och false om den är såld eller bortbytt

        plant.setStatus(false);

        // du får lägga till alla setter här som vi gjorde i exemplet i skolan annars körs värdena över
        plantRepository.save(plant);

        // men vi sätter värdet på amount till värdet vi får i request bodyn
        transaction.setAmount(amount);
        // samma sak som ovan alla setter så du inte kör över alla värden.
        transactionRespository.save(transaction);
        // sen får du göra en return här då såklart
        return ResponseEntity.ok(transaction);
    }




    }






