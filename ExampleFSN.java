import java.util.*;

// Problem Statement

// Context:

// You are developing a payment processing system for an online e-commerce platform. The platform supports multiple payment methods, such as Credit Card, PayPal, and Bank Transfer. Each payment method has its unique processing logic and may also have different transaction fee calculations. The platform allows customers to select their preferred payment method at checkout.

// Requirements:

// 	1.	Payment Method Selection:
// 	•	Implement a system where the customer can select a preferred payment method at runtime.
// 	2.	Payment Processing Logic:
// 	•	Each payment method must have its unique processing logic. For example:
// 	•	Credit Card: Verify card details, perform fraud checks, and then process payment.
// 	•	PayPal: Redirect to PayPal for authorization, then process payment.
// 	•	Bank Transfer: Validate account details and initiate a transfer.
// 	3.	Transaction Fee Calculation:
// 	•	Implement different transaction fee calculation strategies for each payment method. For instance:
// 	•	Credit Card: A percentage-based fee on the transaction amount.
// 	•	PayPal: A fixed fee plus a percentage.
// 	•	Bank Transfer: A fixed fee.
// 	4.	Extensibility:
// 	•	The system should be designed to easily accommodate new payment methods and transaction fee strategies in the future without significant changes to the existing codebase.


  
interface PaymentStrategy {
    void processTransaction();
    double calculateTransactionFees(double amount);
}

class CreditCard implements PaymentStrategy {
    @Override
    public void processTransaction() {
        System.out.println("Processing via Credit Card");
    }

    @Override
    public double calculateTransactionFees(double amount) {
        return 0.02 * amount;
    }
}

class Paypal implements PaymentStrategy {
    @Override
    public void processTransaction() {
        System.out.println("Processing via PayPal");
    }

    @Override
    public double calculateTransactionFees(double amount) {
        return 1 + 0.02 * amount;
    }
}

class BankTransfer implements PaymentStrategy {
    @Override
    public void processTransaction() {
        System.out.println("Processing via Bank Transfer");
    }

    @Override
    public double calculateTransactionFees(double amount) {
        return 2;
    }
}

interface PaymentFactory {
    PaymentStrategy createPaymentType(String type);
}

class PaymentCreator implements PaymentFactory {
    public PaymentStrategy createPaymentType(String type) {
        switch (type.toLowerCase()) {
            case "creditcard":
                return new CreditCard();
            case "paypal":
                return new Paypal();
            case "banktransfer":
                return new BankTransfer();
            default:
                throw new IllegalArgumentException("Return no valid Strategy");
        }
    }
}

public class ExampleFSN{
    public static void main(String[] args) {
        PaymentFactory p = new PaymentCreator();
        PaymentStrategy x = p.createPaymentType("creditcard");
        x.processTransaction();
        System.out.println(x.calculateTransactionFees(100));
    }
}
