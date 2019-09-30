package main.win.suroot;

import com.pi4j.io.gpio.*;

public class Light {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        System.out.println("<--Pi4J--> GPIO Control Example ... started.");
        GpioController gpioController = GpioFactory.getInstance();

        final GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        pin.setShutdownOptions(true, PinState.LOW);
        System.out.println("--> GPIO state should be: ON");
        Thread.sleep(5000);
        pin.low();
        System.out.println("--> GPIO state should be: OFF");
        Thread.sleep(5000);
        pin.toggle();
        System.out.println("--> GPIO state should be: ON");
        Thread.sleep(5000);
        pin.toggle();
        System.out.println("--> GPIO state should be: OFF");
        Thread.sleep(5000);
        System.out.println("--> GPIO state should be: ON for only 1 second");
        pin.pulse(1000, true);


        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpioController.shutdown();
        System.out.println("Exiting ControlGpioExample");

    }

}
