package model;
/**
 * This class is the servers registered to each room
 */
public class Server{
    
    private double amountCache;
    private int numProcessors;
    private String brandProcessor;
    private double amountRam;
    private int numDiscs;
    private double discCapacity;
    
    /**
     * Constructor for the Server class
     * @param amountCache Server cache amount
     * @param numProcessors number of server processors
     * @param brandProcessor processor brand
     * @param amountRam amount of server ram
     * @param numDiscs number of server disks
     * @param discCapacity server disk capacity
     */
    public Server(double amountCache, int numProcessors, String brandProcessor, double amountRam, int numDiscs,
            double discCapacity) {
        this.setAmountCache(amountCache);
        this.setNumProcessors(numProcessors);
        this.setBrandProcessor(brandProcessor);
        this.setAmountRam(amountRam);
        this.setNumDiscs(numDiscs);
        this.setDiscCapacity(discCapacity);
    }
      /**
     * get number of server processors
     * @return number of server processors
     */
    public int getNumProcessors() {
        return numProcessors;
    }
    /**
     * Gives a new value to number of server processors
     * @param numProcessors number of server processors
     */
    public void setNumProcessors(int numProcessors) {
        this.numProcessors = numProcessors;
    }
      /**
     * get processor brand
     * @return processor brand
     */
    public String getBrandProcessor() {
        return brandProcessor;
    }
    /**
     * Gives a new value to processor brand
     * @param brandProcessor new processor brand
     */
    public void setBrandProcessor(String brandProcessor) {
        this.brandProcessor = brandProcessor;
    }
      /**
     * get amount of server ram
     * @return amount of server ram
     */
    public double getAmountRam() {
        return amountRam;
    }
    /**
     * Gives a new value to amount of server ram
     * @param amountRam new amount of server ram
     */
    public void setAmountRam(double amountRam) {
        this.amountRam = amountRam;
    }
      /**
     * get number of server disks
     * @return number of server disks
     */
    public int getNumDiscs() {
        return numDiscs;
    }
    /**
     * Gives a new value to number of server disks
     * @param numDiscs new number of server disks
     */
    public void setNumDiscs(int numDiscs) {
        this.numDiscs = numDiscs;
    }
      /**
     * get server disk capacity
     * @return server disk capacity
     */
    public double getDiscCapacity() {
        return discCapacity;
    }
    /**
     * Gives a new value to server disk capacity
     * @param discCapacity new server disk capacity
     */
    public void setDiscCapacity(double discCapacity) {
        this.discCapacity = discCapacity;
    }
      /**
     * get Server cache amount
     * @return Server cache amount
     */
    public double getAmountCache() {
        return amountCache;
    }
    /**
     * Gives a new value to Server cache amount
     * @param amountCache new Server cache amount
     */
    public void setAmountCache(double amountCache) {
        this.amountCache = amountCache;
    }
     /**
     * Create a message with the Server data 
     * @return message with the server data
     */
    public String toString(){

        String message=(
            "\nRAM memory capacity: "+amountRam+
            "\nNumber of discs: "+numDiscs+
            "\nDisc capacity: "+discCapacity
        );

        return message;
    }
}