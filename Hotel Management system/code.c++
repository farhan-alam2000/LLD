class string;
class Date;
class list;

class Hotel{
    string name; 
    int ID; 
    Location hotelLocation;
    list<Room> roomList;
};

class Location{
    int pin;
    string street;
    string area;
    string city;
    string country;
};

class Room {
    string roomNumber;
    RoomStyle roomStyle;
    RoomStatus roomStatus;
    double bookingPrice;
    list<RoomKey> roomKeys;
    list<HouseKeepingLog> houseKeepingLogs;

};

enum RoomStyle 
{
    STANDARD,DELUX,FAMILY_SUITE
};

enum RoomStatus 
{
    AVAILABLE, RESERVED, NOT_AVAILABLE, OCCUPIED, SERVICE_IN_PROGRESS
};

class RoomKey
{
    string keyId;
    string barCode;
    Date issuedAt;
    bool isActive; 
    bool isMaster; //master key

    public:
        void assignRoom(Room room); //assign room key to a particular room
};

class HouseKeepingLog
{
    string description;
    Date startDate;
    int duration;
    HouseKeeper houseKeeper;

    public:
        void addRoom(Room room); //changes room status to service in pro
};

class Person
{
    protected: 
        string name;
        Account accountDetail;
        string phone;
};

class Account
{
    string username;
    string password;
    AccountStatus accountStatus;
};

enum AccountStatus{
    ACTIVE, CLOSED, BLOCKED
};

class HouseKeeper: public Person
{
    public:
        list<Room> getRoomServiced(Date date); //room serviced on a given date
};

class Guest: public Person
{
    Search searchObj;
    Booking bookingObj;

    public:
        list<RoomBooking> getAllRoomBookings();
};

class Receptionist: public Person
{
    Search searchObj;
    Booking bookingObj;

    public:
        void CheckInGuest(Guest guest, RoomBooking bookingInfo);
        void CheckOutGuest(Guest guest, RoomBooking bookingInfo);
};

class Admin: public Person
{
    public:
        void addRoom(Room roomDetail);
        Room deleteRoom(string roomId);
        void editRoom(Room roomDetail);
};

class Search
{
    public:
        List<Room> searchRoom(RoomStyle roomStyle, Date startDate, int duration);
};

class Booking
{
    public:
        RoomBooking createBooking(Guest guestInfo);
        RoomBooking cancelBooking(int bookingId);
};

class RoomBooking
{
    string bookingId;
    Date startDate;
    int durationInDays;
    BookingStatus bookingStatus;
    list<Guest> guestList;
    list<Room> roomList;
    BaseRoomCharge totalRoomCharges;
};

/**
 * Decorator pattern is used to decorate prices here 
 */
interface BaseRoomCharge {
	double getCost();
}

class RoomCharge implements BaseRoomCharge {
	double cost;
	double getCost() {
		return cost;
	}
	void setCost(double cost) {
		this.cost = cost;
	}
}

class RoomServiceCharge implements BaseRoomCharge {
	double cost;
	BaseRoomCharge baseRoomCharge;
	double getCost() {
		baseRoomCharge.setCost(baseRoomCharge.getCost() + cost);
		return baseRoomCharge.getCost();
	}
}

class InRoomPurchaseCharges implements BaseRoomCharge {
	double cost;
	BaseRoomCharge baseRoomCharge;
	double getCost() {
		baseRoomCharge.setCost(baseRoomCharge.getCost() + cost);
		return baseRoomCharge.getCost();
	}
}
