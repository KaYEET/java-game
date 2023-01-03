public class Main {
    private Room currentRoom;

    private void play() {
        startScreen();

    }

    private void startScreen() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        printLocationInfo();
        System.out.println();
    }

    private void printLocationInfo() {
        System.out.println();
    }

    public Main()
    {
        createrooms();
    }

    private void createrooms() {
        Room street, ally, car, policeStation, prison, park, garage, hallway, room, nightshop;

        street = new Room("The main street in front of the house");
        ally = new Room("The allyway next to the house");
        car = new Room("The car on the ally next to the house");
        policeStation = new Room("In front of the street");
        prison = new Room("The prison cell in front of the police station");
        park = new Room("The park behind the street");
        garage = new Room("The garage inside of the house");
        hallway = new Room("Inside of the house");
        room = new Room("Room inside of the house");
        nightshop = new Room("In front of the street next to the police station");

        street.setExits(car, car, car, car);
        ally.setExits(car, garage, street, null);
        car.setExits(null, null, null, ally);
        policeStation.setExits(prison, null, street, null);
        prison.setExits(null, null, null, policeStation);
        park.setExits(street, null, null, null);
        garage.setExits(null, hallway, null, ally);
        hallway.setExits(null, room, street, garage);
        room.setExits(null, null, null, hallway);
        nightshop.setExits(null, null, street, null);
    }


    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }


}