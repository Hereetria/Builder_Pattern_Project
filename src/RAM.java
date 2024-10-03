public class RAM {
    private BIOS bios;

    public RAM() {
        bios = new BIOS();
    }

    public void loadOSKernelImage() {
        System.out.println("RAM: Loading the image of OS kernel.");
        bios.start();
        System.out.println("RAM: Image of OS kernel has been loaded.");
    }

    public void loadOS() {
        System.out.println("OS has been loaded.");
    }

    public void loadDrivers() {
        System.out.println("Drivers have been loaded.");
    }

    public void startServices() {
        System.out.println("Services have started.");
    }

    // Main method to test the RAM class
    public static void main(String[] args) {
        RAM ram = new RAM();
        ram.loadOSKernelImage();
        ram.loadOS();
        ram.loadDrivers();
        ram.startServices();
    }
}
