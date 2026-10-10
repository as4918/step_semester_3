import java.util.*;
class Product {
    String name; double price;
    Product(String n,double p){name=n;price=p;}
}
interface PaymentMethod { boolean processPayment(double amount); String name(); }
class CreditCardPayment implements PaymentMethod {
    boolean succeeds; CreditCardPayment(boolean s){succeeds=s;}
    public boolean processPayment(double amount){return succeeds;}
    public String name(){return "Credit Card";}
}
class PayPalPayment implements PaymentMethod {
    boolean succeeds; PayPalPayment(boolean s){succeeds=s;}
    public boolean processPayment(double amount){return succeeds;}
    public String name(){return "PayPal";}
}
class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount){return true;}
    public String name(){return "Bank Transfer";}
}
class Order {
    String id,customer,status="Pending"; Map<Product,Integer> items=new LinkedHashMap<>();
    Order(String id,String customer){this.id=id;this.customer=customer;System.out.println("Order created for "+customer+".");}
    void add(Product p,int quantity){if(quantity>0)items.put(p,quantity);}
    double total(){double sum=0;for(Product p:items.keySet())sum+=p.price*items.get(p);return sum;}
    void pay(PaymentMethod method) {
        if(items.isEmpty()){System.out.println("Cannot process payment for an empty order.");return;}
        System.out.println("Payment initiated via "+method.name()+" for Order "+id+".");
        if(method.processPayment(total())){status="Paid";System.out.println("Payment for Order "+id+" successful. Order status: "+status+".");}
        else System.out.println("Payment for Order "+id+" failed. Order status: "+status+".");
    }
}
public class PaymentProcessingSystem {
    public static void main(String[] args) {
        Order x=new Order("X","Customer X");x.add(new Product("A",20),2);x.add(new Product("B",10),1);
        x.pay(new CreditCardPayment(true));
        Order y=new Order("Y","Customer Y");y.pay(new CreditCardPayment(true));
        Order z=new Order("Z","Customer Z");z.add(new Product("C",40),1);z.pay(new PayPalPayment(false));
    }
}
