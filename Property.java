public class Property {
    private String city;
    private String street;
    private int numberOfRooms;
    private double price;
    private String type;
    private boolean forRent;
    private int houseNumber;
    private int floor;
    private String postedBy;
    public User owner ;


    public Property(String city, String street, String type, int floor, int numberOfRooms, int houseNumber, boolean forRent, double price, User owner) {
        this.city = city;
        this.street = street;
        this.type = type;
        this.floor = floor;
        this.numberOfRooms = numberOfRooms;
        this.houseNumber = houseNumber;
        this.forRent = forRent;
        this.price = price;
        this.owner = owner;
    }
    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getType() {
        return type;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public boolean isForRent() {
        return forRent;
    }

    public double getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }
    public String toString () {
        String result = city + " - " + street + " " + houseNumber + ".\n";
        result += type + " - " + (forRent ? "for rent: " : "for sale: ") + numberOfRooms + " rooms";
        if (type.equals("Regular apartment")) {
            result += ", floor " + floor;
        }


        return result;
    }

}
