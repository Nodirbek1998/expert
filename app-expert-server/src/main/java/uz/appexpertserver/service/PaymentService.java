package uz.appexpertserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.appexpertserver.entity.PayType;
import uz.appexpertserver.entity.Payment;
import uz.appexpertserver.entity.Project;
import uz.appexpertserver.entity.enums.ProjectStatus;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqPayment;
import uz.appexpertserver.repository.PayTypeRepository;
import uz.appexpertserver.repository.PaymentRepository;
import uz.appexpertserver.repository.ProjectRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;
    @Autowired
    ProjectRepository projectRepository;

    public ApiResponse addPayment(ReqPayment reqPayment) throws ParseException {
        Payment payment = new Payment();
        savePayment(reqPayment,payment);
        paymentRepository.save(payment);
        return new ApiResponse(true,"Payment saqlandi");
    }

    public ApiResponse editPayment(ReqPayment reqPayment, UUID id) throws ParseException {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bunday to`lov turi mavjud emas"));
        savePayment(reqPayment,payment);
        paymentRepository.save(payment);
        return new ApiResponse(true,"Payment o`zgartirildi");
    }


    private void savePayment(ReqPayment reqPayment, Payment payment) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = simpleDateFormat.parse(reqPayment.getPayDate());
        PayType payType = payTypeRepository.findById(reqPayment.getPayType()).orElseThrow(() -> new ResourceNotFoundException("Bunday to`lov turi topilmadi"));
        Project project = projectRepository.findById(reqPayment.getProject()).orElseThrow(() -> new ResourceNotFoundException("Bunday proyekt topilmadi"));
        project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);
        payment.setAmount(reqPayment.getAmount());
        payment.setPayType(payType);
        payment.setProject(project);
        payment.setPayDate(new Timestamp(date.getTime()));

    }
}
