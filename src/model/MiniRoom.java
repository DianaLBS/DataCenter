package model;
import java.util.ArrayList;
/**
 * This class is the mini-room of the datacenter
 */
public class MiniRoom{
    /**
     * arraylist of servers registered in the mini-room
     */
    ArrayList<Server> servers;
    /**
     * Relationship of the mini-room with the company or project that rents it
     */
    private Company myCompa; 

    private int number;
    private boolean window;
    private double price;
    private boolean available;
    private String rentalDate;
    /**
     * Mini-room class constructor,create the server arraylist and the company object
     * @param number mini-room number
     * @param window Boolean variable that indicates if it has a window or not
     * @param price normal mini-room price
     * @param available Boolean variable that indicates if the mini-room is available or rented
     * @param rentalDate rental date of the mini-room
     */
    public MiniRoom(int number, boolean window, double price,boolean available, String rentalDate) {
        this.number = number;
        this.window = window;
        this.price = price;
        this.available = available;
        this.rentalDate = rentalDate;
        servers= new ArrayList<Server>();
        myCompa=new Company("","");
    }
     /**
     * get company
     * @return company object
     */
    public Company getMyCompa() {
        return myCompa;
    }
    /**
     * Gives a new value to company
     * @param myCompa new company object
     */
    public void setMyCompa(Company myCompa) {
        this.myCompa = myCompa;
    }
     /**
     * get rental Date
     * @return rental Date
     */
    public String getRentalDate() {
        return rentalDate;
    }
    /**
     * Gives a new value to rental Date
     * @param rentalDate new rental Date
     */
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }
     /**
     * get miniroom availability
     * @return availability
     */
    public boolean isAvailable() {
        return available;
    }
    /**
     * Gives a new value to  miniroom availability
     * @param available new availability
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
     /**
     * get mini-room price
     * @return mini-room price
     */
    public double getPrice() {
        return price;
    }
    /**
     * Gives a new value to mini-room price
     * @param price new mini-room price
     */
    public void setPrice(double price) {
        this.price = price;
    }
      /**
     * get if it has a window
     * @return if it has a window
     */
    public boolean isWindow() {
        return window;
    }
    /**
     * Gives a new value to if it has a window
     * @param window new if it has a window
     */
    public void setWindow(boolean window) {
        this.window = window;
    }
     /**
     * get mini-room number
     * @return mini-room number
     */
    public int getNumber() {
        return number;
    }
    /**
     * Gives a new value to mini-room number
     * @param number new mini-room number
     */
    public void setNumber(int number) {
        this.number = number;
    }
    /**
     * Create a message with the mini-room data 
     * @return message with the mini-room data
     */
    public String toString(){
        String windowV;
        if(window){
            windowV="YES";
        }
        else{
            windowV="NO";
        }
        String message=(
            "\nMiniRoom Number #"+number+
            "\nHas window: "+windowV+
            "\nNormal Rental value: "+price

        );

        return message;
    }
     /**
     * add the servers to the mini-room arraylist
     * @param servers1[] array of servers to add
     */
    public void registerServers(Server servers1[]){
        for(int i=0; i<servers1.length;i++){
            servers.add(servers1[i]);
        }
    }
     /**
     *add to the tenant company
     * @param name company name
     * @param nit company nit or project registration number
     */
    public void addCompany(String name, String nit){
        myCompa.setName(name);
        myCompa.setNit(nit); 
    }
     /**
     * get tenant Company Name
     * @return tenant company name
     */
    public String getCompanyName(){
        String name=myCompa.getName();
        return name;
    }
     /**
     * get tenant Company Nit or project registration number
     * @return tenant company nit or project registration number
     */
    public String getCompanyNit(){
        String nit=myCompa.getNit();
        return nit;
    }
     /**
     * get number of servers in the mini-room
     * @return number of servers in the mini-room
     */
    public int getLengthServers(){
        int length=servers.size();
        return length;
    }
    /**
     * create a message with the data of all the servers in the room
     * @return messsage with the data of all the servers in the room
     */
    public String toStringServers(){
        String message="";
        for(int i=0; i<servers.size();i++){
            message+=("\n \n-------Server Number: "+(i+1)+"-------");
            message+=servers.get(i).toString()+"\n";
        }
        return message;
    }
    /**
     * Delete all servers registered in the room
     */
    public void clearArrayList(){
        servers.clear();
    }
}