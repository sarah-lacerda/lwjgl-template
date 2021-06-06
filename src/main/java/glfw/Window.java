package glfw;

import glfw.listener.KeyListener;
import glfw.listener.WindowResizeListener;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.Color;

import java.util.Objects;

import static geometry.configuration.World.setCoordinatePlane;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeLimits;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DONT_CARE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.system.MemoryUtil.NULL;
import static util.Color.WHITE;

public class Window {
    private int width;
    private int height;
    private long glfwWindowAddress;

    private static Window INSTANCE = null;

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 600;
    private static final int MAX_WIDTH = GL_DONT_CARE;
    private static final int MAX_HEIGHT = GL_DONT_CARE;
    private static final String TITLE = "Hello World!";
    private static final Color BACKGROUND_COLOR = WHITE;

    private Window() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    public static Window getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Window();
        }
        return INSTANCE;
    }

    public void run() {
        init();
        execution();
        terminateGracefully();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        boolean glfwStarted = glfwInit();

        // Throw error and terminate if GLFW initialization fails
        if (!glfwStarted) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowAddress = createAndConfigureWindow();

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindowAddress);


        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        setCoordinatePlane();
        setListeners();
        // Make the window visible
        glfwShowWindow(glfwWindowAddress);
    }


    private void execution() {
        // This is the main loop
        while (!glfwWindowShouldClose(glfwWindowAddress)) {
            keyListenerExample();
            renderSampleSquare();
            glfwPollEvents();
        }
    }

    /**
    * This is an example on how to use the implemented {@link KeyListener}
    * In this example, color is set from red to blue while the SPACE key is pressed on the keyboard
    * */
    private void keyListenerExample() {
        if (KeyListener.getInstance().isKeyPressed(GLFW_KEY_SPACE)) {
            glColor3f(0, 0, 1f);
        } else {
            glColor3f(1f, 0, 0);
        }
    }

    private void renderSampleSquare() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(BACKGROUND_COLOR.getRed(), BACKGROUND_COLOR.getGreen(), BACKGROUND_COLOR.getBlue(), 0.0f);

        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(0, -1f);
        glVertex2f(1f, -1f);
        glVertex2f(1f, 0);
        glEnd();

        glfwSwapBuffers(glfwWindowAddress);
    }

    private long createAndConfigureWindow() {
        // Create the window
        long windowAddress = glfwCreateWindow(this.width, this.height, TITLE, NULL, NULL);

        if (windowAddress == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // glfw window Configuration
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwSetWindowSizeLimits(windowAddress, MIN_WIDTH, MIN_HEIGHT, MAX_WIDTH, MAX_HEIGHT);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        return windowAddress;
    }

    private void setListeners() {
        glfwSetKeyCallback(glfwWindowAddress, KeyListener.getInstance());
        glfwSetWindowSizeCallback(glfwWindowAddress, WindowResizeListener.getInstance());
    }

    private void terminateGracefully() {
        // Free memory upon leaving
        glfwFreeCallbacks(glfwWindowAddress);
        glfwDestroyWindow(glfwWindowAddress);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }
}
