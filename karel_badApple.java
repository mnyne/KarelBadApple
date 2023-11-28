import stanford.karel.KarelWorld;
import stanford.karel.SuperKarel;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class karel_badApple extends SuperKarel {
	public int color;
	public int frame_count;

	public void run() {
		for (frame_count = 1580; frame_count <= 1595; frame_count++) {
			drawImage();
			setDelay();
			clearCanvas();
		}
	}

	public void drawImage() {
		for (int ih = 0; ih < 38; ih++) {
			drawLine(ih);
			if (ih == 37)
				break;
			moveToNextLine();
		}
	}

	public void moveToNextLine() {
		turnAround();
		for (int m = 0; m < 49; m++) {
			move();
		}
		turnLeft();
		move();
		turnLeft();
	}

	public void drawLine(int ih) {
		for (int iw = 0; iw < 50; iw++) {
			color = getColor(ih, iw);
			if (color == 0) {
				putBeeper();
				putBeeper();
			} else {
				putBeeper();
				pickBeeper();
			}
			if (iw == 49)
				break;
			move();
		}
	}

	public int getColor(int ih, int iw) {
		try {
            // Lade die Bilddatei
            File imageFile = new File("bad_apple_" + frame_count + ".png");
            BufferedImage image = ImageIO.read(imageFile);

            // Koordinaten des Pixels, dessen Farbwert du ermitteln möchtest
            int x = iw; // x-Koordinate
            int y = ih; // y-Koordinate
         // Ermittle den Farbwert des Pixels
            color = getPixelValue(image, x, y);

            // Gib den ermittelten Wert aus
            

        } catch (IOException e) {
            e.printStackTrace();
        }
		return color;
    }

	 private int getPixelValue(BufferedImage image, int x, int y) {
	        // Verwende die Raster-Klasse für einen schnellen Zugriff auf die Pixelwerte
	        color = image.getRaster().getSample(x, y, 0);

	        return color;
	 }


	public void clearCanvas() {
		turnAround();
		for (int ih = 0; ih < 38; ih++) {
			for (int i = 0; i < 50; i++) {
				if (beepersPresent()) {
					pickBeeper();
					pickBeeper();
				} else {
					putBeeper();
					pickBeeper();
				}
				if (frontIsBlocked())
					break;
				move();
			}
			if (ih == 37)
				break;
			turnRight();
			move();
			turnRight();
			for (int im = 0; im < 49; im++) {
				move();
			}
			turnAround();
		}
		turnAround();
	}

	public void setDelay() {
		for (int i = 0; i < 3000000; i++) {
			turnAround();
		}
	}
}