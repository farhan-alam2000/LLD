// Users

public class Guest {
	  public bool registerAccount();
}

public abstract class Person {
	  private String name;
	  private Address address;
	  private String email;
	  private String phone;

	  /* Has a relationship*/
	  private Account account; 
}

public class Address {
	  private String streetAddress;
	  private String city;
	  private String state;
	  private String zipCode;
	  private String country;
}

public class Account {
	  private String id;
	  private String password;
	  private AccountStatus status;

	  /**
	   * Reset account password.
	   * @return
	   */
	  public boolean resetPassword() {
		  
	  }
}

enum AccountStatus{};

public class Customer extends Person {
	  public boolean makeBooking(Booking booking);
	  public List<Booking> getBookings();
}

public class FrontDeskOfficer extends Person {
	public boolean createBooking(Booking booking);
}

public class Admin extends Person {
  public boolean addMovie(Movie movie);
  public boolean addShow(Show show);
  public boolean blockUser(Customer customer);
}

//Search functionality
public interface Search {

	/**
	 * Search movice based on title.
	 * */
	List<Movie> searchByTitle(String title);

	/**
	 * Search movice based on Langauge.
	 * @param language
	 * @return
	 */
	List<Movie> searchByLanguage(String language);

	/**
	 * Search movice based on genre.
	 * @param genre
	 * @return
	 */
	List<Movie> searchByGenre(String genre);

	/**
	 * Search movice based on Release Date.
	 * @param releaseDate
	 * @return
	 */
	List<Movie> searchByReleaseDate(Date releaseDate);


	/**
	 * Search movice based on city name.
	 * @param cityName
	 * @return
	 */
	public List<Movie> searchByCity(String cityName);
}

public class Catalog implements Search {
	HashMap<String, List<Movie>> movieTitles;
	HashMap<String, List<Movie>> movieLanguages;
	HashMap<String, List<Movie>> movieGenres;
	HashMap<Date, List<Movie>> movieReleaseDates;
	HashMap<String, List<Movie>> movieCities;

	public List<Movie> searchByTitle(String title) {
		return movieTitles.get(title);
	}

	public List<Movie> searchByLanguage(String language) {
		return movieLanguages.get(language);
	}

	//...

	public List<Movie> searchByCity(String cityName) {
		return movieCities.get(cityName);
	}

	@Override
	public List<Movie> searchByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> searchByReleaseDate(Date releaseDate) {
		// TODO Auto-generated method stub
		return null;
	}
}

// Booking Functionality
public class Booking {
	  private String bookingNumber;
	  private int numberOfSeats;
	  private Date createdOn;
	  private BookingStatus status;

	  private Show show;
	  private List<ShowSeat> seats;
	  private Payment payment;

	  /*
	   * Make payment for outstanding amount
	   */
	  public boolean makePayment(Payment payment) {
		  
	  }
	  
	  /**
	   * Cancel existing booking
	   * @return
	   */
	  public boolean cancel() {
		  
	  }
	  
	  /**
	   * @param seats
	   * allocate the seat.
	   * @return
	   */
	  public boolean assignSeats(List<ShowSeat> seats) {
		  
	  }

}

// Payment functionality
public class Payment {
	  private double amount;
	  private Date createdOn;
	  private int transactionId;
	  private PaymentStatus status;	
}

// we can extend it to different types of payment modes - cash, credit card 


// Association of shows -> cinema hall -> cinema -> city 
// & shows -> seat ->

public class Movie {
	  private String title;
	  private String description;
	  private int durationInMins;
	  private String language;
	  private Date releaseDate;
	  private String country;
	  private String genre;
	  private Admin movieAddedBy;

	  private List<Show> shows;
	  
	  /**
	   * List down all shows for the movie.
	   * */
	  public List<Show> getShows(){
		  
	  }
}


public class Show {
	  private int showId;
	  private Date createdOn;
	  private Date startTime;
	  private Date endTime;
	  private CinemaHall playedAt;
	  private Movie movie;
}

public class ShowSeat extends CinemaHallSeat{
	  private int showSeatId;
	  private boolean isReserved;
	  private double price;
}

public class Cinema {
	  private String name;
	  private int totalCinemaHalls;
	  private Address location;

	  private List<CinemaHall> halls;
}

public class CinemaHall {
	  private String name;
	  private int totalSeats;

	  private List<CinemaHallSeat> seats;
	  private List<Show> shows;

}

public class CinemaHallSeat {
	private int seatRow;
	private int seatColumn;
	private SeatType seatType;
}

public class City {
	
	  private String name;
	  private String state;
	  private String zipCode;

}



