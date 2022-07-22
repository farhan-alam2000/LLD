class string;
class List;
class Date;
// started from the base systems i.e. Libarary and defined all the complex classes in it to decouple the system as much as possible
class Library
{
    string name;
    Location location;
    List<BookItem> books;
};

class Location
{
    int pinCode;
    string street;
    string area;
    string city;
    string country;
};

class Book
{
    string uniqueId;
    string title;
    List<string> authors;
    BookType bookType;
};

enum BookType
{
    Romance,
    Action,
    Academic
};

class BookItem : public Book
{
    string barCode;
    Date publicationDate;
    Rack rackLocation;
    BookStatus bookStatus;
    BookFormat bookFormat;
    Date issueDate;
};

enum BookStatus
{
    AVAILABLE,
    ISSUED,
    RESERVED,
    LOST
};

enum BookFormat
{
    HARDCOVER,
    PAPERBACK,
    NEWSPAPER,
    JOURNAL
};

class Rack
{
    int number;
    string locationId;
};

// defining the actors

class Member
{
    string name;
};

class Author : public Person
{
    List<Book> booksPublished;
};

class User : public Person
{
    string email;
    string phoneNumber;
    Account account;
};

class Member : public User
{
    int totalBooksCheckedOut;

    Search seacrhObj;
    BookIssueService issueService;
};

class Librarian : public User
{
    Search seacrhObj;
    BookIssueService issueService;

public:
    void addBookItem(BookItem bookItem);
    BookItem editBookItem(BookItem bookItem);
    BookItem removeBookItem(BookItem bookItem);
};

class Account
{
    string username;
    string password;
    AccountStatus accountStatus;
}

enum AccountStatus {
    ACTIVE,
    CLOSED,
    BLOCKED
};

class Search
{
public:
    List<BookItem> searchByTitle(string title);
    List<BookItem> searchByAuthor(Author author);
    List<BookItem> searchByBookType(BookType bookType);
    List<BookItem> searchByPublicationDate(Date publicationDate);
};

class BookIssueService
{
    FineService fineService;

public:
    BookReservationDetail getReservationDetail(BookItem book);
    void updateReservationDetail(BookReservationDetail bookReservationDetail);
    BookReservationDetail reserveBook(BookItem book, User user);
    BookIssueDetail issueBook(BookItem book, User user);
    BookIssueDetail renewBook(BookItem book, User user);
    void returnBook(BookItem book, User user);
};

class BookLending{
    BookItem book;
    Date startDate;
    User user;
};

class BookReservationDetail: public BookLending
{
    ReservationStatus reservationStatus;
};

enum ReservationStatus{
    RESERVED, UNAVAILABLE, UNRESERVED
};

class BookIssueDetail: public BookLending
{
    Date dueDate;
};

class FineService{
    public:
        Fine calculateFine(BookItem book, User user, int days);
};

class Fine
{
    Date fineDate;
    BookItem book;
    User user;
    double fineValue;
};