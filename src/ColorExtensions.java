import java.awt.*;

public class ColorExtensions {

    private static final int LOWEST = 0;

    /**
     * Return a random color instance.
     *
     * @return Color instance that contains random values for Red, Green and Blue
     */
    public static Color getRandomColor() {
        final int HIGHEST = 255;
        final int RANGE = HIGHEST - LOWEST + 1;

        int r = (int) (Math.random() * RANGE) + LOWEST;
        int g = (int) (Math.random() * RANGE) + LOWEST;
        int b = (int) (Math.random() * RANGE) + LOWEST;

        return new Color(r, g, b);
    }

    /**
     * Return a color that is biased towards the brighter/lighter portion of the light spectrum
     *
     * @return Color instance that is in the brighter/lighter portion of the color spectrum
     */
    public static Color getRandomBrightColor() {
        final int HIGHEST = 255;
        final int LOWEST = 128;
        final int RANGE = HIGHEST - LOWEST + 1;

        StringBuilder hexColor = new StringBuilder();
        hexColor.append("#");
        // generate the three parts of the color
        for (int j = 0; j < 3; j++) {
            int k = (int) (Math.random() * RANGE) + LOWEST;
            ;
            hexColor.append(String.format("%02X", k));
        }
        return Color.decode(hexColor.toString());
    }

    /**
     * Return a color that is biased towards the darker portion of the light spectrum
     *
     * @return Color instance that is in the darker portion of the color spectrum
     */
    public static Color getRandomDarkColor() {
        final int HIGHEST = 127;
        final int RANGE = HIGHEST - LOWEST + 1;

        int r = (int) (Math.random() * RANGE) + LOWEST;
        int g = (int) (Math.random() * RANGE) + LOWEST;
        int b = (int) (Math.random() * RANGE) + LOWEST;

        return new Color(r, g, b);
    }

    /**
     * Return a color that is biased towards the darker portion of the light spectrum
     * while simultaneously being sufficiently different in total R+G+B than the comparison
     * Color instance by the specified total R+G+B amount.
     *
     * @return Color instance that is in the darker portion of the color spectrum
     * @apiNote We look for a dark color that is sufficiently different from the comparision Color instance.
     */
    public static Color getRandomDarkColor(Color compareColor, int byTotalRGB) {
        Color c;
        do {
            c = getRandomDarkColor();
        } while (!areSignificantlyDifferentColors(compareColor, c, byTotalRGB));

        return c;
    }

    /**
     * If two Color instances are significantly different from each other,
     * in terms of their total RGB values, return true; otherwise, return false.
     *
     * @param c1         - a Color instance
     * @param c2         - a Color instance
     * @param byTotalRGB - the expected minimum total RGB difference between the two colors
     * @return - true if the total RGB difference is less than or equal to the expected value;
     * otherwise, return false.
     */
    public static boolean areSignificantlyDifferentColors(Color c1, Color c2, int byTotalRGB) {
        return getColorDifference(c1, c2) <= byTotalRGB;
    }

    /**
     * @param c1 - a Color instance
     * @param c2 - a Color instance
     * @return the absolute value integer total difference (R + G + B) between the two colors
     */
    public static int getColorDifference(Color c1, Color c2) {
        int totalRGB1 = c1.getRed() + c1.getGreen() + c1.getBlue();
        int totalRGB2 = c2.getRed() + c2.getGreen() + c2.getBlue();
        return Math.abs(totalRGB1 - totalRGB2);
    }
}
