package project.praca.shop.admin.order.service;

import project.praca.shop.admin.order.model.AdminOrderStatus;

public class AdminOrderEmailMessage {

    public static String createProcessingEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " jest przetwarzane." +
                "\nStatus został zmieniony na: " + newStatus.getValue() +
                "\nTwoje zamówienie jest przetwarzane przez naszych prcowników"+
                "\nPo skompletowaniu niezwłocznie przekażemy je do wysyłki" +
                "\n\n Pozdrawiamy" +
                "\n JKSKIS";
    }

    public static String createCompletedEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zrealizowane." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Dziekujemuy za zakupy i zapraszamy ponownie" +
                "\n JKSKIS";
    }

    public static String createRefundEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zwrócone." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Pozdrawiamy" +
                "\n JKSKIS";
    }
}