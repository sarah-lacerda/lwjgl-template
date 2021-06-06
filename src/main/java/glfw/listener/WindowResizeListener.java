package glfw.listener;

import glfw.Window;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;

import static geometry.configuration.World.setCoordinatePlane;
import static org.lwjgl.glfw.GLFW.glfwSetWindowAspectRatio;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;

public class WindowResizeListener implements GLFWWindowSizeCallbackI {
    private static WindowResizeListener INSTANCE;

    private WindowResizeListener() {
    }

    public static WindowResizeListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WindowResizeListener();
        }
        return INSTANCE;
    }

    @Override
    public void invoke(long window, int width, int height) {
        reshape(window, width, height);
        Window.getInstance().setWidth(width);
        Window.getInstance().setHeight(height);
    }

    private void reshape(long window, int width, int height) {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glViewport(0, 0, width, height);
        setCoordinatePlane();
        glfwSetWindowAspectRatio(window, width, height);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }
}
