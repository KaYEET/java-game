public class Room {
    public String description;
    public Room north;
    public Room south;
    public Room east;
    public Room west;
    public Room(String description) {
        this.description = description;
    }

    public void setExits(Room north, Room east, Room south, Room west) {
        if(north != null)
            this.north = north;
        if(east != null)
            this.east = east;
        if(south != null)
            this.south = south;
        if(west != null)
            this.west = west;
    }
    public String getDescription() {
        return description;
    }
}
