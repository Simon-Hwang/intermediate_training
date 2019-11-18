import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import ireader.*;


public class ImageProcessorTest {
	private String filePath = "/home/fisco-bcos/Desktop/intermediate_training/bmptest/";
	private ImplementImageProcessor processor = new ImplementImageProcessor();
	private Image image1;
	private Image image2;
	
	@Before
	public void initilize() throws Exception{ 
		image1 = ImageIO.read(new File(filePath + "1.bmp"));
	    image2 = ImageIO.read(new File(filePath + "2.bmp")); // constructor -> set the image
	}
	@Test
	public void test1() throws IOException  { //test showChannelR
		Image imageRed1 = ImageIO.read(new File(filePath + "goal/1_red_goal.bmp"));
		Image imageRed2 = ImageIO.read(new File(filePath + "goal/2_red_goal.bmp"));
		Image red1 = processor.showChanelR(image1);
		Image red2 = processor.showChanelR(image2);
		assertEquals(true, compare(red1, imageRed1));
		assertEquals(true, compare(red2, imageRed2));
	}
	
	@Test
	public void test2() throws IOException  { //test showChannelG
		Image imageGreen1 = ImageIO.read(new File(filePath + "goal/1_green_goal.bmp"));
		Image imageGreen2 = ImageIO.read(new File(filePath + "goal/2_green_goal.bmp"));
		Image green1 = processor.showChanelG(image1);
		Image green2 = processor.showChanelG(image2);
		assertEquals(true, compare(green1, imageGreen1));
		assertEquals(true, compare(green2, imageGreen2));
	}
	
	@Test
	public void test3() throws IOException  { //test showChannelB
		Image imageBlue1 = ImageIO.read(new File(filePath + "goal/1_blue_goal.bmp"));
		Image imageBlue2 = ImageIO.read(new File(filePath + "goal/2_blue_goal.bmp"));
		Image blue1 = processor.showChanelB(image1);
		Image blue2 = processor.showChanelB(image2);
		assertEquals(true, compare(blue1, imageBlue1));
		assertEquals(true, compare(blue2, imageBlue2));
	}
	
	@Test
	public void test4() throws IOException  { //test showGray
		Image imageGray1 = ImageIO.read(new File(filePath + "goal/1_gray_goal.bmp"));
		Image imageGray2 = ImageIO.read(new File(filePath + "goal/2_gray_goal.bmp"));
		Image gray1 = processor.showGray(image1);
		Image gray2 = processor.showGray(image2);
		assertEquals(true, compare(gray1, imageGray1));
		assertEquals(true, compare(gray2, imageGray2));
	}
	
	public boolean compare(Image img1, Image img2) {
	    BufferedImage buffer1 = getBuffer(img1);
	    BufferedImage buffer2 = getBuffer(img2);//get the bufferImage
	    if (img1.getHeight(null) != img2.getHeight(null) || img1.getWidth(null) != img2.getWidth(null)) {
	    	return false;//compare the foundation info
	    }
	    for (int row = 0; row < img1.getHeight(null); row++) {
	      for (int col = 0; col < img1.getWidth(null); col++) {
	    	//compare the [r,g,b] value-> in fact, its is 32bits like [r,r,r] or [g,g,g] or [b,b,b]
	        if (buffer1.getRGB(col, row) != buffer2.getRGB(col, row)) { 
	        	return false;
	        }
	      }
	    }
	    return true;
	  }
	
	public BufferedImage getBuffer(Image img) {
	    BufferedImage buffer = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_RGB);
	    buffer.getGraphics().drawImage(img, 0, 0, null);
	    return buffer;
	  }
}
