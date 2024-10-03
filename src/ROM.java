public class ROM {
    private final int size;  // Size of ROM in MB

    public ROM(int size) {
        this.size = size;
    }

    public byte[] getOSKernelImage() {
        System.out.println("ROM: Loading the image of OS kernel.");
        byte[] kernelImage = new byte[2 * 1024 * 1024];

        // ...

        return kernelImage;
    }

    // Getter for size (optional)
    public int getSize() {
        return size;
    }
}
