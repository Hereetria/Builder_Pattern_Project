public class Client {
    public static void main(String[] args) {
        BaseComputerBuilderI baseBuilder = Computer.getBaseBuilder("My Computer");
        ComputerDisplayBuilderI displayBuilder = baseBuilder.buildRAM().buildCPU().buildHardDrive().buildBaseComputer();
        AccessoryBuilderI accessoryBuilder = displayBuilder.buildGraphicCard().buildComputerDisplay();
        Computer computerWithDisplayAndAccessories = accessoryBuilder.buildKeyboard().buildMouse().build();
        computerWithDisplayAndAccessories.start();


    }
}
