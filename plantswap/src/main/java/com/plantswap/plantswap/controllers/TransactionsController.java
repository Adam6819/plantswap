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
    public ResponseEntity<Transactions> updatefield(@PathVariable String id){

        // Här säger vi om plantan inte finns tillgånglig så kastar vi ett fel
        if (!plantRepository.existsById(id) && !userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant and user not found");

            //För att uppdatera en transaction så ska vi kolla om vi hittar rätt Id på rätt planta och rätt id på rätt user
        }else if (plantRepository.existsById(id) && userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "transaction is now accepted.");
        //här borde jag lägga en cod för att ändra status på plantan från available till såld eller reserverad ????
     updateTransaction(id).setAmount(updateTransaction(id).getAmount());





    }



    }


