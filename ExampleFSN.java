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

import java.util.*;

// Strategy interface for payment processing
interface PaymentStrategy {
    void processTransaction();
    double calculateTransactionFees(double amount);
}

// Concrete strategy for credit card payment
class CreditCard implements PaymentStrategy {
    private static final double FEE_PERCENTAGE = 0.02;

    @Override
    public void processTransaction() {
        System.out.println("Processing via Credit Card");
    }

    @Override
    public double calculateTransactionFees(double amount) {
        return FEE_PERCENTAGE * amount;
    }
}

// Concrete strategy for PayPal payment
class Paypal implements PaymentStrategy {
    private static final double FEE_PERCENTAGE = 0.02;
    private static final double FIXED_FEE = 1.0;

    @Override
    public void processTransaction() {
        System.out.println("Processing via PayPal");
    }

    @Override
    public double calculateTransactionFees(double amount) {
        return FIXED_FEE + (FEE_PERCENTAGE * amount);
    }
}

// Concrete strategy for bank transfer payment
class BankTransfer implements PaymentStrategy {
    private static final double FIXED_FEE = 2.0;

    @Override
    public void processTransaction() {
        System.out.println("Processing via Bank Transfer");
    }

    @Override
    public double calculateTransactionFees(double amount) {
        return FIXED_FEE;
    }
}

// Factory interface for creating payment strategies
interface PaymentFactory {
    PaymentStrategy createPaymentType(PaymentType type);
}

// Concrete factory for creating payment strategies
class PaymentCreator implements PaymentFactory {
    @Override
    public PaymentStrategy createPaymentType(PaymentType type) {
        switch (type) {
            case CREDIT_CARD:
                return new CreditCard();
            case PAYPAL:
                return new Paypal();
            case BANK_TRANSFER:
                return new BankTransfer();
            default:
                throw new IllegalArgumentException("Invalid payment type: " + type);
        }
    }
}

// Enum for payment types
enum PaymentType {
    CREDIT_CARD,
    PAYPAL,
    BANK_TRANSFER
}

public class HelloWorld {
    public static void main(String[] args) {
        PaymentFactory paymentFactory = new PaymentCreator();
        
        // Choose a payment type
        PaymentType paymentType = PaymentType.CREDIT_CARD;
        
        PaymentStrategy paymentStrategy = paymentFactory.createPaymentType(paymentType);
        paymentStrategy.processTransaction();
        System.out.println("Transaction fees: " + paymentStrategy.calculateTransactionFees(100));
    }
}
