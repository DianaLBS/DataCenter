package ui;
import model.*;
import java.util.Scanner;

/**
 * This class allows user interaction with the mini-rooms of the DataCenter
 * @author Diana Balanta
 * @version 1.0
 */

public class Main {
    /**
	 * relation with DataCenter class
	 */
    public static DataCenter myDataCenter;
    /**
	 * the scanner that will allow reading user inputs
	 */
    public static Scanner scanner;
    
    /**
	 * Main method that runs the program
	 * create the DataCenter object
	 * @param args String of characters entered by console.
	 */
    public static void main (String[]args){
        scanner= new Scanner (System.in);
        System.out.println(DataCenter.YELLOW+"Welcome to the DataCenter! "+DataCenter.RESET+"\nBefore starting please enter the base price of the mini-rooms");
        double basePrice=scanner.nextDouble();//Ask for a base price for the mini-rooms
        scanner.nextLine();
		myDataCenter=new DataCenter(basePrice); 
        showMenu();
    }
    /**
	 * Displays the main menu.
	 */
    public static void showMenu(){
        boolean continuee=true;
        while(continuee==true){
            System.out.println(DataCenter.YELLOW+"----------------------------Main Menu-----------------------------"+DataCenter.RESET);
            System.out.println("Enter the option: \n1:Show list of available mini-rooms \n2:Rent a mini-room \n3:Cancel mini-room rental \n4:Show a datacenter map \n5:Simulate the turning on of all mini-rooms \n6:Simulate turning off the mini-rooms \n0:Exit");
            int optionM= scanner.nextInt();
            scanner.nextLine();
            int rentedMiniRooms=myDataCenter.totalRentedMiniRooms();

            switch(optionM){
                case 1: 
                showList();
                break;

                case 2: 
                if(rentedMiniRooms<DataCenter.TOTAL_MINIROOMS){
                    rent();
                }
                else{
                    System.out.println("No mini-rooms available for rent");
                }
                break;

                case 3: 
                if(rentedMiniRooms>0){
                    cancelRent();
                }
                else{
                    System.out.println("There are no rented mini-rooms");
                }
                break;

                case 4:
                showDataCenterMap();
                break;

                case 5:
                simulateTurnOn();
                break;

                case 6:
                simulateTurnOff();
                break;
                
                case 0: 
                continuee=false;
                break;

                default: System.out.println("Incorrect option"); 
            }
        }
    }
    /**
     * Show a list of available mini-rooms
     */
    public static void showList(){
        String message="\n"+myDataCenter.showListAvailable();
        System.out.println(message);
    }
    /**
     * Enter the data to rent a mini-room depending on the type of company
     */
    public static void rent(){
        showDataCenterMap();
        System.out.println("Enter the number of the mini-room you want to rent:");
        int numRoom=scanner.nextInt();
        scanner.nextLine();
        boolean available=myDataCenter.checkRoomAvailability(numRoom);
        if(available){
            System.out.println("Enter the type of rent: \n1: Company \n2: Project");
            int typeRent=scanner.nextInt();
            scanner.nextLine();
            String nameCompany="";
            String nitCompany="";

            switch(typeRent){
                case 1:
                System.out.println("Enter company name:");
                nameCompany=scanner.nextLine();
                System.out.println("Enter company nit:");
                nitCompany=scanner.nextLine();
                break;
                case 2:
                nameCompany=Company.COMPANY_PROJECT;
                System.out.println("Enter the project registration number:");
                nitCompany=scanner.nextLine();
                break;
                default:
                System.out.println("Incorrect Option"); 
            }
            if (typeRent>=1 && typeRent<=2){
                System.out.println("Enter the rental date:");
                String rentalDate=scanner.nextLine();
                System.out.println("Enter the number of servers to register");
                int numServers=scanner.nextInt();
                scanner.nextLine();
                registerServers(numServers,numRoom);
                double totalPrice=myDataCenter.totalPrice(numRoom,numServers);
                System.out.println("The rental value is: "+totalPrice);
                boolean rented=myDataCenter.rent(numRoom,rentalDate,nameCompany,nitCompany);
                if(rented){
                    System.out.println("Successfully rented mini-room");
                }
            }
        }
        else{
            System.out.println("Sorry, This mini-room is not available for rent");
        }
    }

    /**
     * Register the servers of a rented mini-room
     * @param num number of servers
     * @param room room number
     */
    public static void registerServers(int num,int room){
        Server[]servers=new Server[num];
        for(int i=0; i<num;i++){
            System.out.println("SERVER: "+(i+1));
            System.out.println("Enter amount of cache memory (in GB):");
            double cache=scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Number of processors:");
            int numPro=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter processor brand:");
            String brand=scanner.nextLine();
            System.out.println("Enter amount of RAM memory (in GB):");
            double ram=scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Number of discs:");
            int numDiscs=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Disc capacity (in teras):");
            double discCap=scanner.nextDouble();
            scanner.nextLine();
           servers[i]=new Server(cache,numPro,brand,ram,numDiscs,discCap);
        }
        myDataCenter.registerServers(servers, room);
    }
    /**
     * Cancel the rent of one or all the mini-rooms rented by a company or project
     */
    public static void cancelRent(){
        System.out.println("Choose the type of rent to cancel: \n1: Company \n2:Project");
        int typeRoom=scanner.nextInt();
        scanner.nextLine();
        String nit="";
        switch(typeRoom){ //If it is a company or project
            case 1:
            System.out.println("Enter the nit of the company:");
            nit=scanner.nextLine();
            System.out.println("Enter the name of the company:");
            String name=scanner.nextLine();
            int found=myDataCenter.searchCompany(name, nit);
            if(found>0){//If the company has rented a room
                System.out.println("These are the mini-rooms associated with the Company:");
                String miniRoomsRented=myDataCenter.listOfRented(name,nit);
                System.out.println(miniRoomsRented);
                System.out.println("Choose a option: \n1:Cancel the rent of a mini-room for this company \n2: Cancel the rents of all the mini-rooms of this company");
                int optionCancel=scanner.nextInt();
                scanner.nextLine();
                switch(optionCancel){//Choose whether to cancel all the mini-rooms rented by the company or just one
                    case 1:
                    System.out.println("Enter the number of the room to cancel:");
                    int numRoom=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("These are the servers for the mini-room:");
                    String listServers=myDataCenter.listServers(numRoom);
                    System.out.println(listServers);
                    System.out.println("Are you sure you want to cancel this rental and remove the servers? \n1: YES \n2:NO");
                    int deleteServers=scanner.nextInt();
                    scanner.nextLine();
                    if(deleteServers==1){//The servers of the rooms to cancel the rent are shown. And wondering if you are sure to delete them
                        boolean cancelled=myDataCenter.cancelRent(nit,numRoom);
                        if (cancelled){
                            System.out.println("Rent successfully canceled");
                        }
                    }
                    else{
                        System.out.println("The operation was canceled. No rental room was cancelled");
                    }
                    break;
                    case 2://Cancel all the rents of that company
                    System.out.println("These are the servers for the all mini-rooms of this Company:");
                    String totallistServers=myDataCenter.listServers(name,nit);
                    System.out.println(totallistServers);
                    System.out.println("Are you sure you want to cancel this rental and remove the servers? \n1: YES \n2:NO");
                    int deleteS=scanner.nextInt();
                    scanner.nextLine();
                    if(deleteS==1){
                        boolean cancelledAll=myDataCenter.cancelRent(nit);
                        if (cancelledAll){
                            System.out.println("All the rents of this project were canceled");
                        }
                    }
                    else{
                        System.out.println("The operation was canceled. No rental room was cancelled");
                    }

                    break;
                    default:System.out.println("Incorrect Option");
                }
            }
            else{
                System.out.println("Error, name or nit is wrong");
            }
            break;
            case 2://Project
            System.out.println("Choose a option: \n1:Cancel rents associated with a project \n2: Cancel all rents associated with the ICESI company");
            int typeProject=scanner.nextInt();
            scanner.nextLine();
            switch(typeProject){//Ask if you want to cancel the rents of a single project or cancel all the rents on behalf of the ICESI university
                case 1://Project
                System.out.println("Enter the project registration number:");
                nit=scanner.nextLine();
                int searchRoom=myDataCenter.searchProject(nit);
                if(searchRoom>0){//Check that there are rented projects
                    System.out.println("These are the mini-rooms associated with the project:");
                    String miniRoomsRented=myDataCenter.listOfRented(Company.COMPANY_PROJECT,nit);
                    System.out.println(miniRoomsRented);
                    System.out.println("Choose a option: \n1:Cancel the rent of a mini-room for this project \n2: Cancel the rents of all the mini-rooms of this project");
                    int option=scanner.nextInt();
                    scanner.nextLine();
                    switch(option){//Decide whether to cancel just one project rent or all the rent from that project
                        case 1://just one
                        System.out.println("Enter the number of the room to cancel:");
                        int numRoom=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("These are the servers for the mini-room:");
                        String listServers=myDataCenter.listServers(numRoom);
                        System.out.println(listServers);
                        System.out.println("Are you sure you want to cancel this rental and remove the servers? \n1: YES \n2:NO");
                        int deleteServers=scanner.nextInt();
                        scanner.nextLine();
                        if(deleteServers==1){//Ask if you are sure to delete the servers
                            boolean cancelled=myDataCenter.cancelRent(nit,numRoom);
                            if (cancelled){
                                System.out.println("Rent successfully canceled");
                            }
                            else{
                                System.out.println("The operation was canceled. No rental room was cancelled");
                            }
                        }
                    
                        break;
                        case 2:// allrents
                        System.out.println("These are the servers for the mini-rooms:");
                        String totallistServers=myDataCenter.listServers(Company.COMPANY_PROJECT,nit);
                        System.out.println(totallistServers);
                        System.out.println("Are you sure you want to cancel this rental and remove the servers? \n1:YES \n2:NO");
                        int deleteS=scanner.nextInt();
                        scanner.nextLine();
                        if(deleteS==1){//Ask if you are sure to delete the servers
                            boolean cancelledAll=myDataCenter.cancelRent(nit);
                            if (cancelledAll){
                                System.out.println("All the rents of this project were canceled");
                            }
                        }
                        else{
                            System.out.println("The operation was canceled. No rental room was cancelled");
                        }
                        break;
                        default:System.out.println("Incorrect Option");
                    }
                }
                else{
                    System.out.println("No project was found with that registration number");
                }
                break;
                case 2://ICESI
                int searchI=myDataCenter.searchCompany(Company.COMPANY_PROJECT, Company.COMPANY_PROJECT);
                if(searchI>0){//verify that there are rooms rented in the name of ICESI
                    System.out.println("These are the mini-rooms associated with ICESI COMPANY:");
                    String miniRoomsI=myDataCenter.listOfRented(Company.COMPANY_PROJECT);
                    System.out.println(miniRoomsI);
                    System.out.println("These are the servers for the mini-rooms of Company ICESI:");
                    String totallistServers=myDataCenter.listServers(Company.COMPANY_PROJECT,Company.COMPANY_PROJECT);
                    System.out.println(totallistServers);
                    System.out.println("Are you sure you want to cancel this rental and remove the servers? \n1: YES \n2:NO");
                    int deleteS=scanner.nextInt();
                    scanner.nextLine();
                    if(deleteS==1){
                        boolean cancelledAll=myDataCenter.cancelRent(Company.COMPANY_PROJECT);
                        if (cancelledAll){
                            System.out.println("All the rents of this project were canceled");
                        }
                    }
                    else{
                        System.out.println("The operation was canceled. No rental room was cancelled");
                    }
                } 
                else{
                    System.out.println("There is no project");
                }
                break;
                default:
                System.out.println("Incorrect Option");
            }
            break;
            default:
            System.out.println("Incorrect Option");
        }
    }
    /**
     * Shows the map of the datacenter with the number of each mini-room in order of its corridors and columns
     * Shows the available mini-rooms in blue and the rented ones in yellow.
     */
    public static void showDataCenterMap(){
        String message=(
            "\n--------------Datacenter map--------------\n"+
            "Rented / on = "+DataCenter.YELLOW+" YELLOW\n"+DataCenter.RESET+
            "Not rented / Off = "+DataCenter.CYAN+" BLUE"+DataCenter.RESET+"\n"
            );
        message+=myDataCenter.print();
        System.out.println(message);
    }
    /**
     * Simulates that all mini-rooms in the datacenter turn on
     */
    public static void simulateTurnOn(){
        String message="\n-----Simulation: All mini rooms were turned on-----\n";
        message+=myDataCenter.turnOnAll();
        System.out.println(message);
    }

    /**
     * Simulates that the datacenter mini-rooms are turned off according to a way
     */
    public static void simulateTurnOff(){
        boolean continuee=true;
        while(continuee==true){
            System.out.println("\n------Turn off Simulatiom------\n");
            System.out.println(
                "Enter the letter option to turn off: \n"+
                "L: Turns off the first minirooms of all corridors, along with the mini-rooms of the first corridor. \n"+
                "Z: Turns off the mini-quarters of the first and last runners, along with the mini-quarters of the reverse diagonal. \n"+
                "H: Turn off the mini-rooms located in the odd-numbered corridors \n"+
                "O: Turn off the mini-rooms located in the windows \n"+
                "M: Choose a column to turn off \n"+
                "P: Choose a corridor to turn off\n"+
                "0: Exit");
            String option= scanner.nextLine();
            String message="";

            switch(option){
                case "L","l": 
                message=myDataCenter.turnOffLetter("L");
                break;

                case "Z","z": 
                message=myDataCenter.turnOffLetter("Z");
                break;

                case "H","h": 
                message=myDataCenter.turnOffLetter("H");
                break;

                case "O","o":
                message=myDataCenter.turnOffLetter("O");
                break;

                case "M","m":
                System.out.println("Enter the number of the column you want to turn off");
                int colum=scanner.nextInt();
                scanner.nextLine();
                if(colum>=1 && colum<=50){
                    message=myDataCenter.turnOffColum(colum);
                }
                else{
                    System.out.println("Error, remember that the columns are from 1 to 50");
                }
                break;

                case "P","p":
                System.out.println("Enter the number of the corridor you want to turn off");
                int corridor=scanner.nextInt();
                scanner.nextLine();
                if(corridor>=1 && corridor<=8){
                    message=myDataCenter.turnOffCorridor(corridor);
                }
                else{
                    System.out.println("Error, remember that the corridors are from 1 to 8");
                }
                break;
                
                case "0":  continuee=false;
                break;

                default: System.out.println("Incorrect option"); 
            }
            if(message!=""){
                System.out.println(
                    "On = "+DataCenter.GREEN+" GREEN\n"+DataCenter.RESET+
                    "Off = "+DataCenter.RED+" RED\n"+DataCenter.RESET
                );
            }         
            System.out.println(message);
        }
    }
} 