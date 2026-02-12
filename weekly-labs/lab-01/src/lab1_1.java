
abstract class SmartDevice {
    private boolean isOn;

    public SmartDevice(boolean isOn) {
        this.isOn = isOn;
    }

    public SmartDevice() {
        this(true);
    }

    public SmartDevice(SmartDevice other) {
        this.isOn = other.isOn;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public abstract void reset();

    public abstract void statusReport();
}

class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(boolean isOn, int brightness) {
        super(isOn);
        if (brightness >= 0 && brightness <= 100) {
            this.brightness = brightness;
        } else {
            System.out.println("Incorrect value!");
        }
    }

    public SmartLight() {
        super(true);
        this.brightness = 50;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void reset() {
        setBrightness(50);
        setIsOn(true);
    }

    public void statusReport() {
        boolean x = getIsOn();
        if (x) {
            System.out.println("Smart light is On with brightness set to " + brightness);
        } else {
            System.out.println("Smart light is Off with brightness set to " + brightness);
        }
    }

    public void dimLight() {
        if (brightness == 0) {
            System.out.println("Brightness is already low!");
        } else {
            brightness -= 10;
        }
    }

    public void brightenLight() {
        if (brightness == 100) {
            System.out.println("Brightness is already high!");
        } else {
            brightness += 10;
        }
    }
}

class SmartThermostat extends SmartDevice {
    private int temp;

    public SmartThermostat(boolean isOn, int temp) {
        super(isOn);
        this.temp = temp;
    }

    public SmartThermostat() {
        super(true);
        this.temp = 22;
    }

    public int getThermostat() {
        return temp;
    }

    public void setThermostat(int temp) {
        this.temp = temp;
    }

    public void reset() {
        setIsOn(true);
        setThermostat(22);
    }

    public void statusReport() {
        boolean x = getIsOn();
        if (x) {
            System.out.println("Smart Thermostat is On with temperature set to " + temp);
        } else {
            System.out.println("Smart Thermostat is Off with temperature set to " + temp);
        }
    }
}

public class lab1_1 {
    public static void main(String[] args) {
        SmartDevice[] smartHome = new SmartDevice[5];
        smartHome[0] = new SmartLight();
        smartHome[1] = new SmartLight();
        smartHome[2] = new SmartThermostat();
        smartHome[3] = new SmartLight();
        smartHome[4] = new SmartThermostat();
        for (SmartDevice a : smartHome) {
            if (a instanceof SmartLight) {
                ((SmartLight) a).dimLight();
                ((SmartLight) a).turnOff();
            } else if (a instanceof SmartThermostat) {
                // ((SmartThermostat)a).turnOff();
                ((SmartThermostat) a).setThermostat(18);
            }
            a.statusReport();
        }
    }
}
