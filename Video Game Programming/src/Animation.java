import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

	Image[] image;
	int current = 0; /// ignore the next comment// this needs to be 0 if not it
						/// will try to return current at -1
	int delay;
	int duration;

	public Animation(String file, int count, int duration)// count is the number
															// of images in the
															// arr.
	{
		image = new Image[count];

		for (int i = 0; i < count; i++) {
			image[i] = Toolkit.getDefaultToolkit().getImage(file + ".png");
		}

		this.duration = duration;// keep it on the screen for 10 updates
		delay = duration;

	}

	public Image stillImage() {
		return image[0];
	}

	public Image nextImage() {
		if (delay == 0) {// Since the delay is the first thing that happen it
							// will
			current++;         // not happen the 1st time and first current will be 0

			if (current == image.length) {
				current = 1;
			}
			delay = duration;
		} else {

			delay--;

		}

		return image[current];
	}

}
