public interface BaseComputerBuilderI {
    BaseComputerBuilderI buildRAM();
    BaseComputerBuilderI buildCPU();
    BaseComputerBuilderI buildHardDrive();
    ComputerDisplayBuilderI buildBaseComputer();

}
