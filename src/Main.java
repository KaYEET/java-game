public class Main {
    private Room currentRoom;
    private Parser parser;

    public Main() {
        createRooms();
        parser = new Parser();
    }

    private void play() {
        startScreen();
    }

    private void startScreen() {
        System.out.println();
        System.out.println("Welcome to my game!");
        System.out.println("A murder has happened");
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


    private void createRooms() {
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
        currentRoom = hallway;
    }

    private void printHelp() {
        System.out.println("Usable commands");
        System.out.println("-- go quit help --");
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("If you want to quit the game, please only type \"quit\"");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;


        if (direction.equals("north")) {
            nextRoom = currentRoom.north;
        }
        if (direction.equals("east")) {
            nextRoom = currentRoom.east;
        }
        if (direction.equals("south")) {
            nextRoom = currentRoom.south;
        }
        if (direction.equals("west")) {
            nextRoom = currentRoom.west;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if (currentRoom.north != null) {
                System.out.print("north ");
            }
            if (currentRoom.east != null) {
                System.out.print("east ");
            }
            if (currentRoom.south != null) {
                System.out.print("south ");
            }
            if (currentRoom.west != null) {
                System.out.print("west ");
            }
            System.out.println();
        }
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }
}