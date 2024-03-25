
import java.util.* ;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;






public class Theatre {
    //create an arraylist for sorted tickets
    private static ArrayList<Ticket> tickets = new ArrayList<>();



    public static void main(String[] args) {
        // Creating a ragged array to keep track of the seats in each row
        int[][] seats = new int[3][];
        seats[0] = new int[12];
        seats[1] = new int[16];
        seats[2] = new int[20];

        // Initialize all seats to 0
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 0;
            }
        }
        System.out.println("Welcome to the New Theatre!");
        System.out.println("-------------------------------------------------------------------\n");
        Scanner input = new Scanner(System.in);
        int option = -1;
        while (option!=0){
            System.out.println("Please select an option:");
            System.out.println(" 1) Buy a ticket \n 2) Print seating area \n 3) Cancel ticket \n 4) Show available seats \n 5) Save to file \n 6) Load from file \n 7) Print ticket information and total price \n 8) Sort tickets by price \n     0) Quit " );
            System.out.println("-------------------------------------------------------------------");
            System.out.println("\n Enter option:");

            try {
                option = input.nextInt();
                switch (option) {

                    case 1:
                        buy_ticket(seats);
                        break;
                    case 2:
                        print_seating_area(seats);
                        break;
                    case 3:
                        cancel_ticket(seats);
                        break;
                    case 4:
                        show_available(seats);
                        break;
                    case 5:
                        save(seats);
                        break;
                    case 6:
                        load(seats);
                        break;
                    case 7:show_tickets_info();
                        break;
                    case 8:sort_tickets();
                        break;
                    case 0:
                        System.out.println("Terminating program...");
                        break;
                    default:
                        System.out.println("Incorrect input.Please try again");


                        
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                input.next(); // clear the input buffer
                option = -1; // set option to an invalid value to continue the loop
            }

        }
    }



    public static void sort_tickets() {
        //create an arraylist for sorted tickets(it is a copy of tickets arraylist)
        ArrayList<Ticket> sorted_tickets = new ArrayList<>(tickets);

        //sort through arraylist (selection sort)
        for (int i = 0; i < sorted_tickets.size() - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < sorted_tickets.size(); j++) {
                if (sorted_tickets.get(j).getPrice() < sorted_tickets.get(min_index).getPrice()) {
                    min_index = j;
                }
            }
            Ticket temp = sorted_tickets.get(i);
            sorted_tickets.set(i, sorted_tickets.get(min_index));
            sorted_tickets.set(min_index, temp);
        }

        // Print the sorted tickets information
        System.out.println("Sorted tickets by price (ascending):");
        for (Ticket t : sorted_tickets) {
            t.print();//call print method in ticket class
            System.out.println("\n");

        }


    }



    private static void load(int [][]seats) {
        try {
            File file = new File("seats.txt");
            Scanner reader = new Scanner(file);



                for (int i = 0; i < seats.length; i++) {  // iterate through each row
                    String text = reader.nextLine();  // read the corresponding line in the text file and save it to string variable text
                    for (int j = 0; j < seats[i].length; j++){

                    //for each seat, get the corresponding character in text, turn that char into an int and restore it to the seats array
                    seats[i][j]=Character.getNumericValue(text.charAt(j));


                     }
                }


            // close the reader
                reader.close();
            System.out.println("Loaded from file");
        }
        catch (IOException e) {
            System.out.println("Error while reading a file.");
            e.printStackTrace();

        }

    }


    private static void show_available(int [][]seats) {
        //iterate through seats array rows
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Seats available in row "+ (i+1)+": ");
            for (int j = 0; j < seats[i].length; j++) { //iterate through each seat in row
                if (seats[i][j] == 0) {
                    System.out.print((j+1));
                    if (j != seats[i].length - 1) {
                        System.out.print(", ");
                    }
                    else {
                        System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }

    private static void cancel_ticket(int [][]seats) {

        while (true) {
            Scanner input = new Scanner(System.in);
            int rowNum;
            int seatNum;
            try{ System.out.println("Enter row number of ticket you want to cancel:");
                rowNum = input.nextInt();
                System.out.println("Enter seat number of ticket you want to cancel:");
                seatNum = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input. Please try again");
                input.next(); // clear the input buffer
                continue;
            }

            // Check if the seat is a valid seat
            if (rowNum < 1 || rowNum > 3 || seatNum < 1 || seatNum > seats[rowNum - 1].length) {
                System.out.println("Invalid row or seat number. Please try again");

            }

            // Check if the seat is available for cancellation
            else if (seats[rowNum - 1][seatNum - 1] == 0) {
                System.out.println("This seat has not been sold. Please enter correct seat number");

            }

            // Mark the seat as canceled
            else {
                seats[rowNum - 1][seatNum - 1] = 0;


                for (int i = 0; i < tickets.size(); i++) {
                    Ticket ticket = tickets.get(i);
                    if (ticket.getRow() == rowNum && ticket.getSeat() == seatNum) {
                        tickets.remove(i);
                        System.out.println("Ticket cancelled successfully.");
                        break;
                    }
                }
                break;
            }


        }
    }

    public static void print_seating_area(int [][]seats) {
        //align the seating area to the center
        System.out.println("\t\t\t\t***********");
        System.out.println("\t\t\t\t*  STAGE  *");
        System.out.println("\t\t\t\t***********");

        for (int i = 0; i < seats.length; i++) {
            if (i==0) {
                System.out.print("        ");
            } else if (i==1) {
                System.out.print("    ");
            }

            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
                if (j == (seats[i].length/2)-1) { //space in the middle of each row for the aisle in the center
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    }




    private static void buy_ticket(int[][]seats) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = input.next();
        System.out.print("Enter surname: ");
        String surname = input.next();
        System.out.print("Enter E-mail: ");
        String email = input.next();
        //validate e mail input
        while(!email.contains("@")||!email.contains("."))
        {
            System.out.println("E-mail seems to be invalid.Please enter a valid E-mail address.");

            email=input.next(); //empty the input buffer and continue loop

        }


        while (true){
            int rowNum;
            int seatNum;
            try{ System.out.println("Enter preferred row number:");
                rowNum = input.nextInt();
                System.out.println("Enter preferred seat number:");
                seatNum = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input. Please try again");
                input.next(); // clear the input buffer
                continue;
            }

            // Check if the seat is a valid seat
            if (rowNum < 1 || rowNum > 3 || seatNum < 1 || seatNum > seats[rowNum-1].length) {
                System.out.println("Invalid row or seat number. Please try again");

            }

            // Check if the seat is available
            else if (seats[rowNum-1][seatNum-1] == 1) {
                System.out.println("This seat is already sold.");

            }

            else {  // Mark the seat as sold
                seats[rowNum - 1][seatNum - 1] = 1;

                double price = ticket_price(rowNum);
                Person person = new Person(name, surname, email);//create a person object
                Ticket ticket = new Ticket(rowNum, seatNum, price, person);//create new ticket object
                tickets.add(ticket); //add new ticket object to tickets arraylist
                System.out.println("You have successfully bought seat number " + seatNum + " in row " + rowNum);

                break;
            }
        }
    }

    private static void show_tickets_info() {
        double total_price=0;
        for (Ticket t : tickets) {
            // call print method from ticket class
            t.print();
            System.out.println();
        }
        // iterate through tickets arraylist
        for (Ticket t : tickets) {
            total_price=t.getPrice()+total_price;  // getter to get the price and calculate total price

        }
        System.out.println("Total price: "+total_price+ "\n");   // print total price
    }

    // Method to assign a price for each row(seats are less expensive the further they are from the stage)
    public static double ticket_price(int rowNum){
        double price;
        if (rowNum==1){
            price=30.00;
        } else if (rowNum==2) {
            price=20.00;
        }
        else {
            price=10.00;
        }
        return price;

    }





    private static void save(int[][]seats) {
        try{
            FileWriter writer = new FileWriter("seats.txt");

            // iterate though each row
            for (int i = 0; i < seats.length; i++) {

                // iterate though each seat in the row
                for (int j = 0; j < seats[i].length; j++) {

                    // write the seats to the file (as type string)
                    writer.write(seats[i][j]+"");
                }
               writer.write("\n");
            }

            // close file
            writer.close();

            System.out.println("Seats saved to file 'seats.txt'");
        } catch (IOException e) {
            System.out.println("Error saving seats to file: " );
        }

        }


    }

