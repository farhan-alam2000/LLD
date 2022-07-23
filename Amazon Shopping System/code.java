// starting from actors
class Guest{
    Search searchObj;
    public void createAccount();
}

class User
{
    private int UID;
    private String name;
    private String email;
    private String phoneNumber;
    public Account account;
    public Address address;

    Search searchObj;
}

class Account{
    String username;
    String password;
    AccountStatus accountStatus;
}

enum AccountStatus{
    ACTIVE, BLOCKED;
}

class Address{
    int pin;
    String houseNumber;
    String Locality;
    String City;
}

class Seller extends User{
    void addProduct(Product product);
}

class Buyer extends User
{
    ShoppingCart shoppingCart;
    Order orderObj;
    
    void addRating(Rating, Product);
    void addReview(Review, Product);
    // OrderStatus placeOrder(ShoppingCart cart); -> put it in order
}

class Order{
    int orderId;
    List<Items> orderItem;
    Buyer buyer;
    double TotalCost;
    NotificationService notificationService;

    OrderStatus placeOrder();
    OrderStatus trackOrder();
    PaymentStatus makePayment(PaymentType paymentType);
}

enum PaymentType{
    CASH, CARD;
}

enum PaymentStatus{
    PENDING, COMPLETED, REJECTED;
}

enum OrderStatus{
    NOT_PLACED, PLACED, DELIVERED
}

class ShoppingCart{
    List<Items> items;
    double CartValue;

    void addItem(Items item);
    Item removeItem(string productName);
    void modifyItem(Items item);
}

class Items{
    int quantity;
    Product product;
}

class Product
{
    string name;
    double price;
    ProductStatus productStatus;
    ProductType productType;
    CaterogyType category;

    Seller seller;
    List<Review> reviews;
    Rating rating;
}

enum ProductStatus{
    AVAILABLE, SOLD_OUT;
}

enum ProductType{
    FURNITURE, BOOKS;
}

class Rating{
    int rating;
    void giveRating();
}
class Review{
    string review;
    void giveReview();
}

class Search{
    List<Product> searchByName(string name);
    List<Product> searchByCategory(CaterogyType category);
}

enum CaterogyType{
    FURNITURE, BOOKS, ELECTRONICS;
}


// Notification
class NotificationDomain {

	String notificationID;
	NotificationType notificationType;
	User user;

}

class NotificationService {

	

	public boolean sendNotification(NotificationDomain notificationDomain) {

		Notification notificationObject;
		MessageAttribute messageAttribute;

		switch(notificationDomain.getNotificationType()) {


			case NotificationType.EMAIL:
				notificationObject = new EmailNotification();
				messageAttribute = new MessageAttribute("abc@abc.com", notificationDomain.getUser().getAccount().getEmail(),"Order Detail ...");
				break;
			case NotificationType.WHATSAPP:
				notificationObject = new WhatsappNotification();
				messageAttribute = new MessageAttribute("9888888888", notificationDomain.getUser().getAccount().getPhoneNumber(),"Order Detail ...");
				break;
			default:
				notificationObject = new SMSNotification();
				messageAttribute = new MessageAttribute("988888888", notificationDomain.getUser().getAccount().getPhoneNumber(),"Order Detail ...");
				break;
		}

		return notificationObject.sendNotification(messageAttribute);
	}

}

class MessageAttributes {

	String to;
	String from;
	String message;

}

interface Notification {

	boolean sendNotification(MessageAttribute meesageAttribute);

}

class EmailNotification implements Notification {

	boolean sendNotification(MessageAttribute meesageAttribute);
}

class WhatsappNotification implements Notification {

	boolean sendNotification(MessageAttribute meesageAttribute);
}

class SMSNotification implements Notification {

	boolean sendNotification(MessageAttribute meesageAttribute);

}
