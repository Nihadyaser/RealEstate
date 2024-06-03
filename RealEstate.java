import java.util.Scanner;
public class RealEstate {User[] users;
    Property[] properties;
    City[] cities;


    public void createUser(){
        //בדיקת זמינות שם המשתמש: O(n)
        // , כאשר n הוא מספר המשתמשים הקיימים כבר.בדיקת חוזק סיסמה: O(m), כאשר m הוא אורך הסיסמה.בדיקת תקינות מספר הטלפון:
        // O(1), כאשר הבדיקה מתבצעת רק פעם אחת.יצירת אובייקט משתמש חדש: O(1), כאשר האובייקט נוצר רק פעם אחת.לכן
        // , הזמן הכולל הדרוש לביצוע הקוד הוא O(n + m), כאשר n הוא מספר המשתמשים הקיימים ו m הוא אורך הסיסמה.
        Scanner scanner = new Scanner(System.in);
        // Ask for username
        String username;
        do {
            System.out.print("Enter desired username: ");
            username = scanner.nextLine();
            if (!isUsernameAvailable(username)) {
                System.out.println("Username already taken. Please choose another one.");
            }
        } while (!isUsernameAvailable(username));

        // Ask for password
        String password;
        do {
            System.out.print("Enter password (must be at least 5 characters, containing at least one digit and one of the following: $, %, _): ");
            password = scanner.nextLine();
            if (!isStrongPassword(password)) {
                System.out.println("Password is not strong enough. Please try again.");
            }
        } while (!isStrongPassword(password));

        // Ask for phone number
        String phoneNumber;
        do {
            System.out.print("Enter Israeli mobile phone number (10 digits, starting with 05): ");
            phoneNumber = scanner.nextLine();
            if (!isValidIsraeliPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number. Please enter a valid Israeli mobile phone number.");
            }
        } while (!isValidIsraeliPhoneNumber(phoneNumber));


        // Ask if user is a broker or regular user
        System.out.print("Are you a broker or a regular user? ");
        String userType = scanner.nextLine();

        // Create new user object based on input

        User newUser=new User("user","123456k","0501234567");
        if (userType.equalsIgnoreCase("broker")) {
            newUser=new Broker(username, password, phoneNumber);
        } else if (userType.equalsIgnoreCase("regular")){
            newUser= new RegularUser(username, password, phoneNumber);
        }

        // Do something with the new user object (e.g., save to database)
        System.out.println("New user created successfully!");
    }

    public static boolean isUsernameAvailable(String username) {
        // Check if username is available (e.g., check in database)
        // For demonstration purposes, always return true
        return true;
    }

    public static boolean isStrongPassword(String password) {
        // Check if password is strong enough
        return password.length() >= 5 && (password.contains("$") || password.contains("%") || password.contains("_"));
    }
    // password.matches(".\\d.")

    public static boolean isValidIsraeliPhoneNumber(String phoneNumber) {
        // Check if phone number is a valid Israeli mobile phone number
        return phoneNumber.matches("^05[0-9]{8}$");
    }

    class Broker extends User {
        public Broker(String username, String password, String phoneNumber) {
            super(username, password, phoneNumber);
        }
    }

    class RegularUser extends User {
        public RegularUser(String username, String password, String phoneNumber) {
            super(username, password, phoneNumber);
        }
    }


    public User login() {
        User user = login();
        if (user != null) {
            int subChoice = 0;
            do {
                System.out.println("User Menu:");
                System.out.println("1 - Post a new property");
                System.out.println("2 - Remove a property");
                System.out.println("3 - Print all properties");
                System.out.println("4 - Print user's properties");
                System.out.println("5 - Search for a property");
                System.out.println("6 - Logout");
                System.out.print("Enter your choice: ");
                //subChoice = scanner1.nextInt();

                switch (subChoice) {
                    case 1:
                        postNewProperty(user);
                        break;
                    case 2:
                        removeProperty(user);
                        break;
                    case 3:
                        printAllProperties();
                        break;
                    case 4:
                        printProperties(user);
                        break;
                    case 5:
                        search();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            } while (subChoice != 6);
        }
        // implementation here
        return null;
    }

    public boolean postNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);

        int userPropertiesCount = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != null && properties[i].getOwner().equals(user)) {
                userPropertiesCount++;
            }
        }


        if ((user instanceof Broker && userPropertiesCount >= 5) ||
                (!(user instanceof Broker) && userPropertiesCount >= 2)) {
            System.out.println("You have reached the maximum number of properties you can post.");
            return false;
        }


        System.out.println("Available cities:");
        for (City city : cities) {
            if (city != null) {
                System.out.println(city.getCityName());
            }
        }


        System.out.println("Enter city name:");
        String cityName = scanner.nextLine();
        City city = null;
        for (City c : cities) {
            if (c != null && c.getCityName().equalsIgnoreCase(cityName)) {
                city = c;
                break;
            }
        }
        if (city == null) {
            System.out.println("City not found.");
            return false;
        }


        System.out.println("Available streets in " + city.getCityName() + ":");
        for (String street : city.getStreets()) {
            System.out.println(street);
        }


        System.out.println("Enter street name:");
        String street = scanner.nextLine();
        boolean validStreet = false;
        for (String s : city.getStreets()) {
            if (s.equalsIgnoreCase(street)) {
                validStreet = true;
                break;
            }
        }
        if (!validStreet) {
            System.out.println("Street not found.");
            return false;
        }


        System.out.println("Enter property type (1 - Regular apartment, 2 - Penthouse, 3 - Private house):");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();
        String type;
        if (typeChoice == 1) {
            type = "Regular apartment";
        } else if (typeChoice == 2) {
            type = "Penthouse";
        } else if (typeChoice == 3) {
            type = "Private house";
        } else {
            System.out.println("Invalid type.");
            return false;
        }


        int floor = -1;
        if (type.equals("Regular apartment")) {
            System.out.println("Enter floor number:");
            floor = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Enter number of rooms:");
        int rooms = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Enter house number:");
        int houseNumber = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Is the property for rent? (yes/no):");
        boolean forRent = scanner.nextLine().equalsIgnoreCase("yes");


        System.out.println("Enter the price:");
        int price = scanner.nextInt();
        scanner.nextLine();

        Property property = new Property(city.getCityName(), street, type, floor, rooms, houseNumber, forRent, price, user);
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] == null) {
                properties[i] = property;
                System.out.println("Property posted successfully!");
                return true;
            }
        }

        System.out.println("Failed to post property. No available slot.");
        return false;
        // implementation here
        return false;
    }

    public void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);

        int userPropertyIndex = 0;
        Property[] userProperties = new Property[properties.length];
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != null && properties[i].getOwner().equals(user)) {
                userProperties[userPropertyIndex++] = properties[i];
            }
        }

        if (userPropertyIndex == 0) {
            System.out.println("You have no properties to remove.");
            return;
        }


        System.out.println("Your properties:");
        for (int i = 0; i < userPropertyIndex; i++) {
            System.out.println((i + 1) + ". " + userProperties[i]);
        }


        System.out.println("Enter the number of the property you want to remove:");
        int propertyNumber = scanner.nextInt();
        scanner.nextLine();

        if (propertyNumber < 1 || propertyNumber > userPropertyIndex) {
            System.out.println("Invalid property number.");
            return;
        }

        Property propertyToRemove = userProperties[propertyNumber - 1];
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != null && properties[i].equals(propertyToRemove)) {
                properties[i] = null;
                System.out.println("Property removed successfully.");
                return;
            }
        }

        System.out.println("Failed to remove property.");
        // implementation here
    }

    public void printAllProperties() {
        For (property property : properties){
            System.out.println(property);
        }
        // implementation here
    }

    public void printProperties(User user){
        List<property> userproperties = user.getProperties();
        if (userproperties.isEmpty()){
            System.out.println("לא פרסמת נכסים.");
            return;
        }
        for(Property property : userproperties){
            System.out.println(property);
        }
    }

    public Property[] search() {

        public Property[] search() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("הנכס להשכרה או למכירה? (1 - להשכרה, 2 - למכירה, 999 - לא משנה): ");
            int forRentOption = scanner.nextInt();
            Boolean forRent = null;
            if (forRentOption == 1) {
                forRent = true;
            } else if (forRentOption == 2) {
                forRent = false;
            }

            System.out.print("הכנס סוג נכס רצוי (1 - דירה רגילה, 2 - דירת פנטהאוז, 3 - בית פרטי, 999 - לא משנה): ");
            int typeOption = scanner.nextInt();
            scanner.nextLine();  // consume newline
            String propertyType = null;
            switch (typeOption) {
                case 1:
                    propertyType = "דירה רגילה";
                    break;
                case 2:
                    propertyType = "דירת פנטהאוז";
                    break;
                case 3:
                    propertyType = "בית פרטי";
                    break;
                case 999:
                    propertyType = null;
                    break;
                default:
                    System.out.println("סוג נכס לא תקין.");
                    return new Property[0];
            }

            System.out.print("הכנס מספר חדרים רצוי (999 - לא משנה): ");
            int rooms = scanner.nextInt();

            System.out.print("הכנס טווח מחירים (מינימום): ");
            double minPrice = scanner.nextDouble();

            System.out.print("הכנס טווח מחירים (מקסימום): ");
            double maxPrice = scanner.nextDouble();

            List<Property> filteredProperties = new ArrayList<>();
            for (Property property : properties) {
                if ((forRent == null || property.isForRent() == forRent) &&
                        (propertyType == null || propertyType.equals(property.getType())) &&
                        (rooms == 999 || property.getNumberOfRooms() == rooms) &&
                        (minPrice == 999 || property.getPrice() >= minPrice) &&
                        (maxPrice == 999 || property.getPrice() <= maxPrice)) {
                    filteredProperties.add(property);
                }
            }

            return filteredProperties.toArray(new Property[0]);
        }
        return null;
    }
}

