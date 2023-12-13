package project.praca.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.praca.shop.admin.order.model.AdminOrder;
import project.praca.shop.admin.order.model.AdminOrderStatus;
import project.praca.shop.common.email.EmailClientService;

import static project.praca.shop.admin.order.service.AdminOrderEmailMessage.createCompletedEmailMessage;
import static project.praca.shop.admin.order.service.AdminOrderEmailMessage.createProcessingEmailMessage;
import static project.praca.shop.admin.order.service.AdminOrderEmailMessage.createRefundEmailMessage;

@Service
@RequiredArgsConstructor
class EmailNotificationForStatusChange {

    private final EmailClientService emailClientService;

    void sendEmailNotification(AdminOrderStatus newStatus, AdminOrder adminOrder) {
        if(newStatus == AdminOrderStatus.PROCESSING){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + "  zmieniło status na: " + newStatus.getValue(),
                    createProcessingEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.COMPLETED){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zostało zrealizowane",
                    createCompletedEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.REFUND){
            sendEmail(
                    adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zostało zwrócone",
                    createRefundEmailMessage(adminOrder.getId(), newStatus));
        }
    }

    private void sendEmail(String email, String subject, String message) {
        emailClientService.getInstance().send(email, subject, message);
    }
}
