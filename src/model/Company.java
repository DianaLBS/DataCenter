package model;
/**
 * This class is the company that rents a mini-room
 */
public class Company{
    /**
     * Name of project company name
     */
    public final static String COMPANY_PROJECT="ICESI";
    private String nit;
    private String name;
    
/**
 * Constructor of the company class
 * @param nit company nit or registration number of a project
 * @param name company name  or ICESI project
 */
    public Company(String nit, String name){
       this.setNit(nit);
       this.setName(name);
    }

    /**
     * get company name
     * @return company name
     */
    public String getName() {
        return name;
    }

     /**
     * Gives a new value to company name
     * @param name new company name
     */
    public void setName(String name) {
        this.name = name;
    }

     /**
     * get company nit
     * @return company nit
     */
    public String getNit() {
        return nit;
    }

     /**
     * Gives a new value to company nit
     * @param nit new company nit
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * Create a message with the company or project data 
     * @return message with the name and nit of the company or registration number of a project
     */
    public String toString() {
        String message=(
            "\n1: "+name+
            "\n2: "+nit
        );
        return message;
    }
    
}