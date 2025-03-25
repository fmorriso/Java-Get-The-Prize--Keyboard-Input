# Java Get The Prize Keyboard Input

Example of a simple Java AWT + Swing game using two balls, a large moving one and a smaller one
that jumps to a random new location when the large ball collides with it.

The balls also change color when:
* The large ball bounces off any of the four walls.
* The small ball is hit by the large ball.
* NOTE: since the background is white, the random color changes are restricted by
using calls to `getRandomDarkColor()` within `ColorExtensions.java` to insure neither ball disappears or becomes
difficult to see.

Techniques used:
* JFrame
* JPanel
* KeyAdapter
* MouseAdapter
* ActionListener
* Scaling to a percentage of available device screen size (SwingScreenUtilities.java)
* Random color changing (ColorExtensions.java)

## Tools Used

| Tool     |  Version |
|:---------|---------:|
| Java     | 24.0.0.0 |
| IntelliJ | 2024.3.5 |
| VSCode   |   1.98.0 |

## Change History

| Date       | Description      |
|:-----------|:-----------------|
| 2025-03-24 | Initial creation |
