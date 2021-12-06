package model;
/**
 * This is the controller class of the program
 */
public class DataCenter {

    /**
     * Discount for mini-rooms with window
     */
    public final static double DISCOUNT_WINDOW=10;
    /**
     * Discount for the mini-rooms of the seventh corridor
     */
    public final static double DISCOUNT_SEVENTHCORRIDOR=15;
    /**
     * Surcharge for the mini-rooms located between the 2nd and 6th corridor
     */
    public final static double SURCHARGE_BETWEEN2AND6_CORRIDOR=25;
    /**
     * Total mini-rooms
     */
    public final static double TOTAL_MINIROOMS=8*50;
    /**
     * Red color
     */
    public final static String RED="\033[31m";
    /**
     * Green color
     */
    public final static String GREEN="\033[32m";
    /**
     * Cyan color
     */
    public final static String CYAN="\033[36m";
    /**
     * Yellow color
     */
    public final static String YELLOW="\033[33m";
    /**
     * Reset color
     */
    public final static String RESET="\u001B[0m";
    /**
     * Relation with matrix of mini-rooms
     */
    private MiniRoom[][] miniRooms; 

    private double basePrice;
    /**
     * Class constructor
     * create the matrix
     * @param basePrice starting price of all mini-rooms
     */
    public DataCenter(double basePrice) {
        this.basePrice=basePrice;
        miniRooms= new MiniRoom [8][50];
        startMatrix();
    }
    /**
     * Fill the matrix with the initial data of the mini-rooms
     */
    public void startMatrix (){
        int cont=1;
        double price=basePrice;
        double discount=0;
        double surcharge=0;
        boolean window=false;
        for (int i = 0; i < miniRooms.length; i++) {//Note:(En el enunciado no se especifica la manera de aplicar los descuentos)
            for (int j = 0; j < miniRooms[i].length; j++) {//Calculate the price of each mini-room according to its discounts
                if(i==6){//Discount runner 7
                    discount+=DISCOUNT_SEVENTHCORRIDOR;
                }
                if(i>=1 && i<=5){//Surcharge for corridors between 2 and 6
                    surcharge=SURCHARGE_BETWEEN2AND6_CORRIDOR;
                }
                if (i==0 || i==(miniRooms.length-1) ||j==0 ||j==(miniRooms[i].length-1)){
                    window=true; //Discount per window
                    discount+=DISCOUNT_WINDOW;
                    price-=(discount*price)/100;
                    price+=(surcharge*price)/100;  
                    miniRooms[i][j]=new MiniRoom(cont,window,price,true,"");
                }
                else{
                    window=false;
                    price-=(discount*price)/100;
                    price+=(surcharge*price)/100; 
                    miniRooms[i][j]=new MiniRoom(cont,window,price,true,"");  
                }
                cont++;
                discount=0;
                surcharge=0;
                price=basePrice;
            }
        }
    }
    /**
     * Calculate the number of mini-rooms rented
     * @return total rooms rented
     */
    public int totalRentedMiniRooms(){
        int rented=0;
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[i].length; j++) {
                if(miniRooms[i][j].isAvailable()==false){
                    rented++;
                }
            }
        }    
        return rented;
    }
    /**
     * It shows the list of the mini-rooms that are available and their data
     * @return message with the list of available mini-rooms
     */
    public String showListAvailable(){
        String message="----List of available mini-rooms:-----\n";
        for (int i = 0; i < miniRooms.length; i++) {
            for (int j = 0; j < miniRooms[i].length; j++) { 
                if(miniRooms[i][j].isAvailable()){
                    message+="\n"+miniRooms[i][j].toString();
                    message+="\nCorridor number: "+(i+1)+"\nColumn number: "+(j+1);
                }
            }
        } 
        return message;      
    }
    /**
     * Create the datacenter map
     * @return Message with the number of rooms matrix type
     */
    public String print(){
        String message="";
        int num=0;

        for(int i=0; i<miniRooms.length; i++){
            message+=(RESET+"\nCorridor "+(i+1)+" :\n");
            for(int j=0; j<miniRooms[i].length; j++){
            
                if(miniRooms[i][j].getNumber()<10){
                    if(miniRooms[i][j].isAvailable()){
                        message+=(CYAN+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                    }
                    else{
                        message+=(YELLOW+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                    }
                }
                else if(miniRooms[i][j].getNumber()<100){
                    if(miniRooms[i][j].isAvailable()){
                        message+=(CYAN+" [ "+miniRooms[i][j].getNumber()+"] ");
                    }
                    else{
                        message+=(YELLOW+" [ "+miniRooms[i][j].getNumber()+"] ");
                    }
                }
                else{
                    if(miniRooms[i][j].isAvailable()){
                        message+=(CYAN+" ["+miniRooms[i][j].getNumber()+"] ");
                    }
                    else{
                        message+=(YELLOW+" ["+miniRooms[i][j].getNumber()+"] ");
                    }
                }
                num++;
                if(num==25){//It is ordered every 25 mini-rooms to be more pleasing to the eye
                    message+=("\n");
                    num=0;
                }
            }
        }    
        message+=(RESET); 
        return message;
    }
    /**
     * Simulates turning on (in green) all the mini-rooms of the datacenter
     * @return Message with all the mini-rooms of the data center map in green
     */
    public String turnOnAll(){
        String message="";
        int num=0;
        for(int i=0; i<miniRooms.length; i++){
            message+=(RESET+"\nCorridor "+(i+1)+" :\n");
            for(int j=0; j<miniRooms[i].length; j++){
                if(miniRooms[i][j].getNumber()<10){    
                    message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                }
                else if(miniRooms[i][j].getNumber()<100){
                    message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+"] ");
                }
                else{
                   
                    message+=(GREEN+" ["+miniRooms[i][j].getNumber()+"] ");
                }
                num++;
                if(num==25){
                    message+=("\n");
                    num=0;
                }
            }
        }    
        message+=(RESET); 
        return message;
    }
    /**
     * Simulates turning off the mini-rooms of the datacenter according to a letter entered
     * @param letter letter indicating which ones to turn off
     *@return  Message with the data center map mini-rooms turned off (red) based on a letter
     */
    public String turnOffLetter(String letter){
        String message="";
        int num=0;
        switch(letter){
            case "L":
            for(int i=0; i<miniRooms.length; i++){
                message+=(RESET+"\nCorridor "+(i+1)+" :\n");
                for(int j=0; j<miniRooms[i].length; j++){
                    if(i==0 ||j==0){
                        if(miniRooms[i][j].getNumber()<10){    
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                        }
                        else if(miniRooms[i][j].getNumber()<100){
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] ");
                        }
                        else{
                            message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                        }
                    }
                    else if(miniRooms[i][j].getNumber()<100){
                        message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+"] ");
                    }
                    else{
                       
                        message+=(GREEN+" ["+miniRooms[i][j].getNumber()+"] ");
                    }
                    num++;
                    if(num==25){
                        message+=("\n");
                        num=0;
                    }
                }
            }  
            break;
            case "Z":
            for(int i=0; i<miniRooms.length; i++){
                message+=(RESET+"\nCorridor "+(i+1)+" :\n");
                for(int j=0; j<miniRooms[i].length; j++){
                    if(i==0 ||i==miniRooms.length-1){
                        if(miniRooms[i][j].getNumber()<10){    
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                        }
                        else if(miniRooms[i][j].getNumber()<100) {
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] ");
                        }
                        else{
                            message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                        }
                    }
                    else if(miniRooms[i][j].getNumber()<100){
                        if(i+j == miniRooms.length-1){
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] "); 
                        }
                        else{
                            message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+"] ");
                        }
                    }
                    else{
                        if(i+j == miniRooms.length-1){
                            message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                        }
                        else{
                            message+=(GREEN+" ["+miniRooms[i][j].getNumber()+"] ");
                        }
                    }
                    num++;
                    if(num==25){
                        message+=("\n");
                        num=0;
                    }
                }
            } 
            break;
            case "H":
            for(int i=0; i<miniRooms.length; i++){
                message+=(RESET+"\nCorridor "+(i+1)+" :\n");
                for(int j=0; j<miniRooms[i].length; j++){
        
                    if(miniRooms[i][j].getNumber()<10){  
                        if(miniRooms[i][j].getNumber()%2!=0){  
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                        }
                        else{
                            message+=(GREEN+"\033[32m [ "+miniRooms[i][j].getNumber()+" ] ");
                        }
                    }
                    else if(miniRooms[i][j].getNumber()<100){
                        if(miniRooms[i][j].getNumber()%2!=0){ 
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] "); 
                        }
                        else{
                            message+=(GREEN+"\033[32m [ "+miniRooms[i][j].getNumber()+"] ");
                        }
                    }
                    else{
                        if(miniRooms[i][j].getNumber()%2!=0){
                            message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                        }
                        else{
                            message+=(GREEN+"\033[32m ["+miniRooms[i][j].getNumber()+"] ");
                        }
                    }
                    num++;
                    if(num==25){
                        message+=("\n");
                        num=0;
                    }
                }
            } 
            break;
            case "O":
            for(int i=0; i<miniRooms.length; i++){
                message+=("\u001B[0m\nCorridor "+(i+1)+" :\n");
                for(int j=0; j<miniRooms[i].length; j++){
                    if(i==0||i==miniRooms.length-1||j==0||j==miniRooms[i].length-1){
                        if(miniRooms[i][j].getNumber()<10){  
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                        }
                        else if(miniRooms[i][j].getNumber()<100){
                            message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] "); 
                        }
                        else{
                            message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                        }
                    }

                    else if(miniRooms[i][j].getNumber()<10){  
                        message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                    }
                    else if(miniRooms[i][j].getNumber()<100){
                        message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+"] ");
                    }
                    else{
                        
                        message+=(GREEN+" ["+miniRooms[i][j].getNumber()+"] ");
                    }
                    num++;
                    if(num==25){
                        message+=("\n");
                        num=0;
                    }
                }
            } 
            break;
        }
        message+=("\u001B[0m"); 
        return message;
    }
    /**
     * Turn off the mini-rooms according to a column
     * @param num column number
     * @return message with the datacenter map turning off a column
     */
    public String turnOffColum(int num){
        String message="";
        int cont=0;
        for(int i=0; i<miniRooms.length; i++){
            message+=("\u001B[0m\nCorridor "+(i+1)+" :\n");
            for(int j=0; j<miniRooms[i].length; j++){
                if(j+1==num){
                    if(miniRooms[i][j].getNumber()<10){
                        message+=(RED+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                    }
                    else if(miniRooms[i][j].getNumber()<100){
                        message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] ");
                    }
                    else{ 
                        message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                    }
                }
                else if(miniRooms[i][j].getNumber()<10){  
                    message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                }
                else if(miniRooms[i][j].getNumber()<100){
                    message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+"] ");
                }
                else{
                    
                    message+=(GREEN+" ["+miniRooms[i][j].getNumber()+"] ");
                }
                cont++;
                if(cont==25){
                    message+=("\n");
                    cont=0;
                }
            }
        }    
        message+=(RESET); 
        return message; 
    }

    /**
     * Turn off the mini-rooms according to a corridor
     * @param num corridor number
     * @return message with the datacenter map turning off a corridor
     */
    public String turnOffCorridor(int num){
        String message="";
        int cont=0;

        for(int i=0; i<miniRooms.length; i++){
            message+=("\u001B[0m\nCorridor "+(i+1)+" :\n");
            for(int j=0; j<miniRooms[i].length; j++){
                if(i+1==num){
                    if(miniRooms[i][j].getNumber()<10){
                        message+=(RED+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                    }
                    else if(miniRooms[i][j].getNumber()<100){
                        message+=(RED+" [ "+miniRooms[i][j].getNumber()+"] ");
                    }
                    else{ 
                        message+=(RED+" ["+miniRooms[i][j].getNumber()+"] ");
                    }
                }
                else if(miniRooms[i][j].getNumber()<10){  
                    message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+" ] "); 
                }
                else if(miniRooms[i][j].getNumber()<100){
                    message+=(GREEN+" [ "+miniRooms[i][j].getNumber()+"] ");
                }
                else{
                    
                    message+=(GREEN+" ["+miniRooms[i][j].getNumber()+"] ");
                }
                cont++;
                if(cont==25){
                    message+=("\n");
                    cont=0;
                }
            }
        }    
        message+=(RESET); 
        return message; 
    }
    
    /**
     * Check that a mini-room is available
     * @param numberRoom mini-room number
     * @return Boolean variable that says if it is available or not
     */
    public boolean checkRoomAvailability(int numberRoom){
        boolean available=false;
        boolean continuee=true;

        for(int i=0; i<miniRooms.length && continuee==true; i++){
            for(int j=0; j<miniRooms[i].length && continuee==true; j++){
                if(miniRooms[i][j].getNumber()==numberRoom){
                    if(miniRooms[i][j].isAvailable()){
                        available=true;
                        continuee=false;
                    }
                }
            }
        }
        return available;
    }
    /**
     * Register servers to a mini-room
     * @param servers[] array of servers to add
     * @param numRoom mini-room number
     */
    public void registerServers(Server servers[],int numRoom){
        for(int i=0; i<miniRooms.length; i++){
            for(int j=0; j<miniRooms[i].length; j++){
                if(miniRooms[i][j].getNumber()==numRoom){
                    miniRooms[i][j].registerServers(servers);
                }
            }
        }
    }
    /**
     * Calculate the total rental price of a mini-room based on the number of servers
     * @param num mini-room number
     * @param numServers number of registered servers
     * @return total price of the mini-room
     */
    public double totalPrice(int num,  int numServers){
        double total=0;
        double priceServe=15;
        boolean continuee=true;
       
            for(int i=0; i<miniRooms.length && continuee==true; i++){
                for(int j=0; j<miniRooms[i].length && continuee==true; j++){
                    if(miniRooms[i][j].getNumber()==num){
                        if(numServers<4){
                            total=miniRooms[i][j].getPrice()+((priceServe*miniRooms[i][j].getPrice())/100);
                            continuee=false;
                        }
                        else{
                            total=miniRooms[i][j].getPrice();
                        }
                    }
                }
            }
        
        return total;
    }
    /**
     * Change the status of a mini-room to rented
     * @param numRoom mini-room number
     * @param date rental date
     * @param nameCompany name of the company that rents
     * @param nitCompany nit of the company or project registration number
     * @return  Boolean variable that indicates if the mini-room was rented successfully
     */
    public boolean rent(int numRoom, String date, String nameCompany, String nitCompany){
        boolean rented=false;
        boolean continuee=true;
        for(int i=0; i<miniRooms.length && continuee==true; i++){
            for(int j=0; j<miniRooms[i].length && continuee==true; j++){
                if(miniRooms[i][j].getNumber()==numRoom){
                    miniRooms[i][j].setAvailable(false);
                    miniRooms[i][j].setRentalDate(date);
                    miniRooms[i][j].addCompany(nameCompany,nitCompany);
                    continuee=false;
                    rented=true;
                }
            }
        }
        return rented;
    }
    /**
     * Create a list of mini-rooms rented by a company or project
     * @param company name of the company that rents or ICESI
     * @param nit nit of the company or project registration number
     * @return  Message with the list of mini-rooms rented by a company or project
     */
    public String listOfRented(String company, String nit){
        String message="";
        Company comp;
        for(int i=0; i<miniRooms.length; i++){
            for(int j=0; j<miniRooms[i].length; j++){
                if(company==Company.COMPANY_PROJECT){
                    comp=miniRooms[i][j].getMyCompa();
                    if(comp.getNit().equals(nit)){
                        message+="\n----";
                        message+=miniRooms[i][j].toString();
                        if(miniRooms[i][j].getLengthServers()<4){
                            message+="\nTotal rental (for servers):"+totalPrice(miniRooms[i][j].getNumber(), 1);
                        }
                        message+="\n----";
                    }
                }
                else{
                    comp=miniRooms[i][j].getMyCompa();
                    if(comp.getName().equalsIgnoreCase(company)){
                        if(comp.getNit().equals(nit)){
                            message+="\n----";
                            message+=miniRooms[i][j].toString();
                            if(miniRooms[i][j].getLengthServers()<4){
                                message+="\nTotal rental (for servers):"+totalPrice(miniRooms[i][j].getNumber(), 1);
                            }
                            message+="\n----";
                        }
                    }
                }
            }
        } 
        return message;
    }
    /**
     * Create a list of mini-rooms rented by ICESI company
     * @param company name ICESI
     * @return  Message with the list of mini-rooms rented by ICESI
     */
    public String listOfRented(String company){
        String message="";
        Company comp;
        for(int i=0; i<miniRooms.length; i++){
            for(int j=0; j<miniRooms[i].length; j++){ 
                comp=miniRooms[i][j].getMyCompa();
                if(comp.getName().equalsIgnoreCase(Company.COMPANY_PROJECT)){
                    message+="\n----";
                    message+=miniRooms[i][j].toString();
                    if(miniRooms[i][j].getLengthServers()<4){
                        message+="\nTotal rental (for servers):"+totalPrice(miniRooms[i][j].getNumber(), 1);
                    }
                    message+="\n----";
                }
            }
        } 
        return message;
    }
    /**
     * Find that the registration number of a project has rented a mini-room
     * @param nit registration number of a project
     * @return  Boolean variable that indicates whether the record number was found or not
     */
    public int searchProject(String nit){
        int found=0;
        boolean continuee=true;
        for(int i=0; i<miniRooms.length && continuee==true; i++){
            for(int j=0; j<miniRooms[i].length  && continuee==true; j++){
                if(miniRooms[i][j].getCompanyName().equalsIgnoreCase(Company.COMPANY_PROJECT)&& miniRooms[i][j].getCompanyNit().equals(nit)){
                    found=miniRooms[i][j].getNumber();
                    continuee=false;
                }
            }
        }
        return found;
    }
    /**
     * Find that a company has rented a mini-room
     * @param name company name
     * @param nit company nit
     * @return  Boolean variable that indicates whether the compnay  was found or not
     */
    public int searchCompany(String name, String nit){
        int found=0;
        if(name.equalsIgnoreCase(Company.COMPANY_PROJECT)){
            for(int i=0; i<miniRooms.length; i++){
                for(int j=0; j<miniRooms[i].length; j++){
                    if(miniRooms[i][j].getCompanyName().equalsIgnoreCase(name)){
                        found=miniRooms[i][j].getNumber();
                    }
                }
            }
        }
        else{
            for(int i=0; i<miniRooms.length; i++){
                for(int j=0; j<miniRooms[i].length; j++){
                    if(miniRooms[i][j].getCompanyName().equalsIgnoreCase(name) && miniRooms[i][j].getCompanyNit().equals(nit)){
                        found++;
                    }
                }
            }
            
        }
        return found;
    }
     /**
     * Create a list of registered servers in a mini-room
     * @param room mini.room number
     * @return message with servers and their data
     */
    public String listServers(int room){
        boolean continuee=true;
        String message="";
        for(int i=0; i<miniRooms.length && continuee==true; i++){
            for(int j=0; j<miniRooms[i].length  && continuee==true; j++){
                if(miniRooms[i][j].getNumber()==room){
                    message=miniRooms[i][j].toStringServers();
                    continuee=false;
                }
            }
        }
        return message;
    }
     /**
     * Create a list of registered servers in all rented mini-rooms of a company or project
     * @param name company name or ICESI
     * @param nit company nit or registration number of a project
     * @return message with the servers of each mini-room and their capacities
     */
    public String listServers(String name, String nit){
        String message="";
        int cont=0;
        if(nit.equals(Company.COMPANY_PROJECT)){
            for(int i=0; i<miniRooms.length; i++){
                for(int j=0; j<miniRooms[i].length; j++){
                    if(miniRooms[i][j].getCompanyName().equalsIgnoreCase(name)){
                        cont++;
                        message+=YELLOW+"\n \n---Servers of miniRoom number: "+cont+"---"+RESET;
                        message+=miniRooms[i][j].toStringServers();
                    }
                }
            } 
        }
        else{
            for(int i=0; i<miniRooms.length; i++){
                for(int j=0; j<miniRooms[i].length; j++){
                    if(miniRooms[i][j].getCompanyName().equalsIgnoreCase(name) && miniRooms[i][j].getCompanyNit().equals(nit)){
                        cont++;
                        message+=YELLOW+"\n \n---Servers of miniRoom number: "+cont+"---"+RESET;
                        message+=miniRooms[i][j].toStringServers();
                    }
                }
            }
        }
        return message;
    }
    /**
     * Change the status of a mini-room to not rented
     * @param nit company nit or registration number of a project
     * @param room mini-room number
     * @return Boolean variable that indicates if the rent of the mini-room was canceled satisfactorily
     */
    public boolean cancelRent(String nit, int room){
        boolean cancelled=false;
        boolean continuee=true;
        for(int i=0; i<miniRooms.length && continuee==true; i++){
            for(int j=0; j<miniRooms[i].length && continuee==true; j++){
                if(miniRooms[i][j].getCompanyNit().equals(nit) && miniRooms[i][j].getNumber()==room){
                    miniRooms[i][j].setAvailable(true);
                    miniRooms[i][j].addCompany("", "");
                    miniRooms[i][j].clearArrayList();
                    miniRooms[i][j].setRentalDate("");
                    cancelled=true;
                    continuee=false;
                }
            }
        }
        return cancelled;
    }
    /**
     * Change the status of all the mini-rooms of a company or project to not rented
     * @param nit company nit or registration number of a project
     * @return Boolean variable that indicates if the rent of the mini-rooms were canceled satisfactorily
     */
    public boolean cancelRent(String nit){
        boolean cancelled=false;
        if(nit==Company.COMPANY_PROJECT){
            for(int i=0; i<miniRooms.length; i++){
                for(int j=0; j<miniRooms[i].length; j++){
                    if(miniRooms[i][j].getCompanyName().equalsIgnoreCase(nit)){
                        miniRooms[i][j].setAvailable(true);
                        miniRooms[i][j].addCompany("", "");
                        miniRooms[i][j].clearArrayList();
                        miniRooms[i][j].setRentalDate("");
                        cancelled=true;
                    }
                }
            }   
        }
        else{
            for(int i=0; i<miniRooms.length; i++){
                for(int j=0; j<miniRooms[i].length;j++){
                    if(nit.equals(miniRooms[i][j].getCompanyNit())){
                        miniRooms[i][j].setAvailable(true);
                        miniRooms[i][j].addCompany("", "");
                        miniRooms[i][j].clearArrayList();
                        miniRooms[i][j].setRentalDate("");
                        cancelled=true;
                    }
                }
            }   
        }
        return cancelled;
    } 
}