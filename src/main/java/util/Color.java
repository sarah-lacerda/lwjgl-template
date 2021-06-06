package util;

import static java.util.Arrays.stream;

public enum Color {
    BLANK(-1f, -1f, -1f),
    BLACK(0.000f, 0.000f, 0.000f),
    WHITE(1.000f, 1.000f, 1.000f),
    RED(1.000f, 0.000f, 0.000f),
    LIME(0.000f, 1.000f, 0.000f),
    BLUE(0.000f, 0.000f, 1.000f),
    YELLOW(1.000f, 1.000f, 0.000f),
    CYAN_AQUA( 	0.000f, 1.000f, 1.000f),
    MAGENTA(1.000f, 0.000f, 1.000f),
    SILVER(0.753f, 0.753f, 0.753f),
    GRAY(0.502f, 0.502f, 0.502f),
    MAROON(0.502f, 0.000f, 0.000f),
    OLIVE(0.502f, 0.502f, 0.000f),
    GREEN(0.000f, 0.502f, 0.000f),
    PURPLE(0.502f, 0.000f, 0.502f),
    TEAL(0.000f, 0.502f, 0.502f),
    VIOLET(0.933f, 0.510f, 0.933f),
    NAVY(0.000f, 0.000f, 0.502f);

    private final float red;
    private final float green;
    private final float blue;


    public static Color from(String colorName) {
        return stream(Color.values())
                .filter(color -> colorName.toLowerCase().contains(color.name().toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

}
