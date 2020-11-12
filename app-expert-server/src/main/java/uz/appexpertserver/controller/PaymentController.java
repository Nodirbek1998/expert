package uz.appexpertserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.appexpertserver.entity.Payment;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqPayment;
import uz.appexpertserver.repository.PaymentRepository;
import uz.appexpertserver.service.PaymentService;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRepository paymentRepository;
    @PostMapping
    public HttpEntity<?> addPayment(@RequestBody ReqPayment reqPayment) throws ParseException {
        ApiResponse apiResponse = paymentService.addPayment(reqPayment);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse.getMessage());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getPayments(@PathVariable UUID id){
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bunday to`lov turi yo`q"));
        return ResponseEntity.ok(payment);
    }
    @GetMapping
    public HttpEntity<?> getPayments(){
        return ResponseEntity.ok(paymentRepository.findAll());
    }
   @PutMapping(value = "/{id}")
    public HttpEntity<?> editPayment(@RequestBody ReqPayment reqPayment, @PathVariable UUID id) throws ParseException {
       ApiResponse apiResponse = paymentService.editPayment(reqPayment, id);
       return ResponseEntity.ok(apiResponse);
   }
   @DeleteMapping(value = "/{id}")
    public HttpEntity<?> deletePayment(@PathVariable UUID id){
        paymentRepository.deleteById(id);
        return ResponseEntity.ok("Malumot o`chirildi");
   }
}
