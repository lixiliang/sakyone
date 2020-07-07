package iap;

public interface ExpirationIntent {
    int CUSTOMER_CANCELED = 1;
    int BILLING_ERROR = 2;
    int CUSTOMER_NOT_AGREE_PRICE = 3;
    int PRODUCT_NOT_AVAILABLE = 4;
    int UNKNOWN_ERRORS = 5;
}
