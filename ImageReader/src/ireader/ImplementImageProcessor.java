package ireader;

import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

class ColorFilter extends RGBImageFilter {
	  // get the byte of one kind of color
	  private int color;
	
	  public ColorFilter(int c) {
		    color = c; // 1->red 2->green 3->blue  get the specific color channel info
		    canFilterIndexColorModel = true;
	  }
	
	  public int filterRGB(int x, int y, int rgb) {
		    if (color == 1) {
		      return (rgb & 0xffff0000); //0xffff0000 -> red Channel
		    } else if (color == 2) {
		      return (rgb & 0xff00ff00); // 0xff00ff00-> green Channel
		    } else if (color == 3) {
		      return (rgb & 0xff0000ff); // 0xff0000ff-> blue Channel
		    } else {
		      // algorithm of get the Gray
		      int gray = (int) (((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587 + ((rgb & 0x000000ff)) * 0.114);
		      return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray; // turn it into 32 bits(interger)
		    }
	  }
}
	  
public class ImplementImageProcessor implements IImageProcessor {
	  @Override
	  public Image showChanelR(Image sourceImage) {
	    ColorFilter redFilter = new ColorFilter(1);
	    Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), redFilter));// turn into java.awt.image
	    return img;
	  }
	
	  @Override
	  public Image showChanelG(Image sourceImage) {
	    ColorFilter greenFilter = new ColorFilter(2);
	    Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), greenFilter));// turn into java.awt.image
	    return img;
	  }
	
	  @Override
	  public Image showChanelB(Image sourceImage) {
	    ColorFilter blueFilter = new ColorFilter(3);
	    Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), blueFilter));// turn into java.awt.image
	    return img;
	  }
	
	  @Override
	  public Image showGray(Image sourceImage) {
	    ColorFilter grayFilter = new ColorFilter(0);
	    Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter)); // turn into java.awt.image
	    return img;
	  }
}
