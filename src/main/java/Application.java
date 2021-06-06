import glfw.Window;

import static de.damios.guacamole.gdx.StartOnFirstThreadHelper.startNewJvmIfRequired;

public class Application {

    private static final Window WINDOW = Window.getInstance();

    public static void main(String[] args) {
        // Starts a new JVM if the application was started on macOS without the
        // -XstartOnFirstThread argument.
        if (startNewJvmIfRequired()) {
            System.exit(0);
        }
        WINDOW.run();
    }
}
