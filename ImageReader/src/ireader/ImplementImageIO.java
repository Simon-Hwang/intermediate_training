package ireader;

import imagereader.IImageIO;
import java.awt.Image;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.ImageProducer;

public class ImplementImageIO implements IImageIO {
	  private Image img;
	  @Override
	  public Image myRead(String filePath) throws IOException {
		    File file = new File(filePath);
		    FileInputStream fileInStream = new FileInputStream(file);//open the file and load it in the stream
		    fileInStream.skip(14);// the former 14 bytes record the file's header
		    byte bmpInfo[] = new byte[40];
		    fileInStream.read(bmpInfo, 0, 40); // these 40 bytes record the file info
		    int imageWidth = bytesToInt(bmpInfo, 4, 4);
		    int imageHeight = bytesToInt(bmpInfo, 8, 4);
		    int pixelBits = bytesToInt(bmpInfo, 14, 2);
		    int imageSize = bytesToInt(bmpInfo, 20, 4); // 40 bytes record above info and get it
		    if (pixelBits == 24) {
			      int numEmptyByteOfRow = imageSize / imageHeight - 3 * imageWidth; 
			      if (numEmptyByteOfRow == 4) { // extra exactly 32 bit per line-> no need to expand
			    	  numEmptyByteOfRow = 0; // no need to expand 
			      }
			      int pixel = 0;
			      int pixelArray[] = new int[imageWidth * imageHeight]; // get the image info from file
			      byte pixelBytes[] = new byte[imageSize];// 3 * (width * height) -> 3 channel
			      fileInStream.read(pixelBytes, 0, imageSize); // read from file
			      for (int row = imageHeight - 1; row >= 0; row--) {
			    	  for (int col = 0; col < imageWidth; col++) {
				          int pixelIndex = imageWidth * row + col; 
				          pixelArray[pixelIndex] = bytesToInt(pixelBytes, pixel, 3); 
				          pixelArray[pixelIndex] |= (0xff << 24); // 0xff << 24 get the high 8 bits to expand it to [r,b,c] 24bits to 32bits
				          pixel += 3; // get 3 bytes-> [r,g,b] at a time and move to next pixel
			        }
			        pixel += numEmptyByteOfRow; // skip the empty bytes.
			      }
			      // change from pixel array to java.awt.image 
			      // while MemoryImageSorce implements the ImageProducer interface
			      img = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(imageWidth, imageHeight,pixelArray, 0, imageWidth)); 
		    }
		    fileInStream.close(); // chose the file stream
		    return img;
	  }

	  @Override
	  public Image myWrite(Image img, String filepath) throws IOException {
		    try {
		      File imgFile = new File(filepath);
		      BufferedImage buffer = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		      buffer.getGraphics().drawImage(img, 0, 0, null); // draw into the buffer
		      ImageIO.write(buffer, "bmp", imgFile); // write into file
		      return img; 
		    } catch (Exception e) {
		    	
		    }
		    return img;
	  }

	  // java write from litter->  turn bytes to integer
	  private int bytesToInt(byte[] bytes, int offset, int length) {
	    int result = 0;
	    for (int i = 0; i < length; i++) {
	    	result |= (bytes[offset + i] & 0xff) << (i * 8);
	    }
	    return result;
	  }
}
