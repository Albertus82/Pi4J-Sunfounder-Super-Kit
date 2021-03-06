package it.albertus.pi4j.sunfounder.superkit._04_PwmLed;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class DblPwmLed {

	private static final int PWM_MIN_VALUE = 0;
	private static final int PWM_MAX_VALUE = 1024;

	public static void main(final String... args) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();

		final GpioPinPwmOutput ledPin1 = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01, PWM_MIN_VALUE); // HW PWM0 OUT 0
		final GpioPinPwmOutput ledPin2 = gpio.provisionPwmOutputPin(RaspiPin.GPIO_23, PWM_MAX_VALUE); // HW PWM1 OUT 1

		ledPin1.setShutdownOptions(true, PinState.LOW, PinPullResistance.PULL_DOWN); // IN 0
		ledPin2.setShutdownOptions(true, PinState.LOW, PinPullResistance.PULL_DOWN); // IN 0

		while (true) {
			for (int i = PWM_MIN_VALUE; i <= PWM_MAX_VALUE; i++) {
				ledPin1.setPwm(i);
				ledPin2.setPwm(PWM_MAX_VALUE - i);
				Thread.sleep(1);
			}
			for (int i = PWM_MAX_VALUE; i >= PWM_MIN_VALUE; i--) {
				ledPin1.setPwm(i);
				ledPin2.setPwm(PWM_MAX_VALUE - i);
				Thread.sleep(1);
			}
		}
	}

}
