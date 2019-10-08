package main.win.suroot;

import com.pi4j.io.gpio.*;

public class Light {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        System.out.println("<--Pi4J--> GPIO Control Example ... started.");
        GpioController gpioController = GpioFactory.getInstance();

        final GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
        pin.blink(1000);
        Thread.sleep(1000);
        pin.blink(2000);


//        pin.high();
//        Thread.sleep(1000);
//        pin.low();
//        Thread.sleep(2000);
//        pin.high();
//        Thread.sleep(3000);
//        pin.low();
//        Thread.sleep(1000);
//        pin.high();



//        pin.setShutdownOptions(true, PinState.LOW);
//        System.out.println("--> GPIO state should be: ON");
//        Thread.sleep(5000);
//        pin.low();
//        System.out.println("--> GPIO state should be: OFF");
//        Thread.sleep(5000);
//        pin.toggle();
//        System.out.println("--> GPIO state should be: ON");
//        Thread.sleep(5000);
//        pin.toggle();
//        System.out.println("--> GPIO state should be: OFF");
//        Thread.sleep(5000);
//        System.out.println("--> GPIO state should be: ON for only 1 second");


        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpioController.shutdown();
        System.out.println("Exiting ControlGpioExample");

    }

}
