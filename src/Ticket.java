public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // getters and setters for row, seat, price, and person
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void print() {
        System.out.println("Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
    }
}

