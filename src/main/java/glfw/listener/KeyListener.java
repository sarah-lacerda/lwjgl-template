package glfw.listener;

import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener implements GLFWKeyCallbackI {
    private static final int TOTAL_GLFW_KEY_BINDINGS = 350;
    private static KeyListener INSTANCE;
    private final boolean[] keyPressed = new boolean[TOTAL_GLFW_KEY_BINDINGS];

    private KeyListener() {
    }

    public static KeyListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KeyListener();
        }
        return INSTANCE;
    }

    public void invoke(long window, int key, int scanCode, int action, int mods) {
        if (action == GLFW_PRESS) {
            this.keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            this.keyPressed[key] = false;
        }
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode <= TOTAL_GLFW_KEY_BINDINGS) {
            return getInstance().keyPressed[keyCode];
        }
        System.err.printf("INVALID key with keycode= %d pressed%n", keyCode);
        return false;
    }
}
