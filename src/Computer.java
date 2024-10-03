public class Computer {
    private static BaseComputerBuilder baseComputerBuilder;
    private static ComputerDisplayBuilder computerDisplayBuilder;
    private static AccessoryBuilder accessoryBuilder;

    private String name;
    private CPU cpu;
    private RAM ram;
    private HardDrive hd;
    private GraphicCard graphicCard;
    private Display display;
    private Keyboard keyboard;
    private Mouse mouse;

    // Default constructor
    public Computer() {
    }

    // Constructor with 5 parameters
    private Computer(String name, CPU cpu, RAM ram, HardDrive hd, GraphicCard graphicCard) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.hd = hd;
        this.graphicCard = graphicCard;
    }

    // Constructor with 6 parameters
    private Computer(String name, CPU cpu, RAM ram, HardDrive hd, GraphicCard graphicCard, Display display) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.hd = hd;
        this.graphicCard = graphicCard;
        this.display = display;
    }

    // Constructor with 7 parameters
    private Computer(String name, CPU cpu, RAM ram, HardDrive hd, GraphicCard graphicCard,
                     Display display, Keyboard keyboard) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.hd = hd;
        this.graphicCard = graphicCard;
        this.display = display;
        this.keyboard = keyboard;
    }

    // Constructor with 8 parameters
    private Computer(String name, CPU cpu, RAM ram, HardDrive hd, GraphicCard graphicCard,
                     Display display, Keyboard keyboard, Mouse mouse) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.hd = hd;
        this.graphicCard = graphicCard;
        this.display = display;
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void start() {
        this.cpu.start();
        this.ram.loadOSKernelImage();
        this.ram.loadOS();
        this.ram.loadDrivers();
        this.ram.startServices();
        this.hd.start();
        if (this.graphicCard != null) {
            this.graphicCard.start();
        }

        if (this.display != null) {
            this.display.start();
        }

        if (this.keyboard != null) {
            this.keyboard.start();
        }

        if (this.mouse != null) {
            this.mouse.start();
        }

        System.out.println("Computer " + this.name + " has started.\n");
    }

    public String getName() {
        return this.name;
    }


    public CPU getCpu() {
        return this.cpu;
    }


    public RAM getRam() {
        return this.ram;
    }


    public HardDrive getHd() {
        return this.hd;
    }

    public GraphicCard getGraphicCard() {
        return this.graphicCard;
    }

    public Display getDisplay() {
        return this.display;
    }

    public Keyboard getKeyboard() {
        return this.keyboard;
    }


    public Mouse getMouse() {
        return this.mouse;
    }

    public static BaseComputerBuilderI getBaseBuilder(String name) {
        baseComputerBuilder = new BaseComputerBuilder(name);
        return baseComputerBuilder;
    }

    private static ComputerDisplayBuilderI getDisplayBuilder() {
        computerDisplayBuilder = new ComputerDisplayBuilder(baseComputerBuilder);
        return computerDisplayBuilder;
    }

    private static AccessoryBuilderI getAccessoryBuilder() {
        accessoryBuilder = new AccessoryBuilder(computerDisplayBuilder);
        return accessoryBuilder;
    }

    public static class BaseComputerBuilder implements BaseComputerBuilderI {
        private String name;
        private boolean hasCpu = false;
        private boolean hasRam = false;
        private boolean hasHardDrive = false;
        private Computer computer;

        public BaseComputerBuilder(String name) {
            this.name = name;
        }

        @Override
        public BaseComputerBuilderI buildRAM() {
            this.hasRam = true;
            return this;
        }

        @Override
        public BaseComputerBuilderI buildCPU() {
            this.hasCpu = true;
            return this;
        }

        @Override
        public BaseComputerBuilderI buildHardDrive() {
            this.hasHardDrive = true;
            return this;
        }

        @Override
        public ComputerDisplayBuilderI buildBaseComputer() {
            this.computer = new Computer();
            this.computer.name = this.name;

            if (this.hasRam) {
                RAM ram = new RAM();
                this.computer.ram = ram;
            }
            if (this.hasCpu) {
                CPU cpu = new CPU();
                this.computer.cpu = cpu;
            }
            if (this.hasHardDrive) {
                HardDrive hd = new HardDrive();
                this.computer.hd = hd;
            }
            ComputerDisplayBuilder computerDisplayBuilder = (ComputerDisplayBuilder) Computer.getDisplayBuilder();
            computerDisplayBuilder.computer = this.computer;
            return computerDisplayBuilder;
        }
    }

    public static class ComputerDisplayBuilder implements ComputerDisplayBuilderI {

        private boolean hasGraphicCard = false;
        private boolean hasDisplay = false;
        private Computer computer;

        public ComputerDisplayBuilder(BaseComputerBuilder baseComputerBuilder) {
            this.computer = baseComputerBuilder.computer;
        }

        @Override
        public ComputerDisplayBuilderI buildGraphicCard() {
            this.hasGraphicCard = true;
            return this;
        }

        @Override
        public ComputerDisplayBuilderI buildDisplay() {
            this.hasDisplay = true;
            return this;
        }

        @Override
        public AccessoryBuilderI buildComputerDisplay() {
            if (this.hasGraphicCard) {
                GraphicCard graphicCard = new GraphicCard();
                this.computer.graphicCard = graphicCard;
            }
            if (this.hasDisplay) {
                Display display = new Display();
                this.computer.display = display;
            }
            AccessoryBuilder accessoryBuilder = (AccessoryBuilder) Computer.getAccessoryBuilder();
            accessoryBuilder.computer = this.computer;
            return accessoryBuilder;
        }
    }

    public static class AccessoryBuilder implements AccessoryBuilderI {

        private boolean hasMouse = false;
        private boolean hasKeyboard = false;
        private Computer computer;

        public AccessoryBuilder(ComputerDisplayBuilder computerDisplayBuilder) {
            this.computer = computerDisplayBuilder.computer;
        }

        @Override
        public AccessoryBuilderI buildMouse() {
            this.hasMouse = true;
            return this;
        }

        @Override
        public AccessoryBuilderI buildKeyboard() {
            this.hasKeyboard = true;
            return this;
        }

        @Override
        public Computer build() {
            if (this.hasMouse) {
                Mouse mouse = new Mouse();
                this.computer.mouse = mouse;
            }
            if (this.hasKeyboard) {
                Keyboard keyboard = new Keyboard();
                this.computer.keyboard = keyboard;
            }
            return this.computer;
        }

    }
}
