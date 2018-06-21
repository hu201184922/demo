package com.fairyland.jdp.orm.util.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.MemoryImageSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PicTool
{
  private static final Log logger = LogFactory.getLog(PicTool.class);

  public static final BufferedImage pressImage(Object press, Object target, int x, int y)
  {
    try
    {
      BufferedImage src = null;
      BufferedImage src_biao = null;

      if ((target instanceof String)) {
        File _file = new File(target.toString());
        return pressImage(press, _file, x, y);
      }if ((target instanceof File))
        src = ImageIO.read((File)target);
      else if ((target instanceof BufferedImage)) {
        src = (BufferedImage)target;
      }

      if ((press instanceof String)) {
        File _file = new File(press.toString());
        return pressImage(_file, src, x, y);
      }if ((press instanceof File))
        src_biao = ImageIO.read((File)press);
      else if ((press instanceof BufferedImage))
        src = (BufferedImage)press;
      else {
        throw new IOException("press 参数不符合约定");
      }
      if(src_biao != null && src != null)
        return pressImage(src_biao, src, x, y);
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }
    return null;
  }

  public static final BufferedImage pressImage(BufferedImage press, BufferedImage target, int x, int y)
  {
    int wideth = target.getWidth(null);
    int height = target.getHeight(null);
    BufferedImage image = new BufferedImage(wideth, height, 1);
    Graphics g = image.createGraphics();
    g.drawImage(target, 0, 0, wideth, height, null);

    int wideth_biao = press.getWidth(null);
    int height_biao = press.getHeight(null);
    g.drawImage(press, wideth - wideth_biao - x, height - height_biao - y, wideth_biao, height_biao, null);
    g.dispose();
    return image;
  }

  public static void write(BufferedImage image, String filePath)
  {
    try {
      write(image, new FileOutputStream(filePath));
    } catch (FileNotFoundException e) {
    	logger.error(e.getMessage());
    }
  }

  public static void write(BufferedImage image, OutputStream out) {
    try {
      ImageIO.write(image, "JPEG", out);
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }
  }

  public static BufferedImage pressText(String pressText, String targetImg, String fontName, int fontStyle, int color, int fontSize, int x, int y)
  {
    try
    {
      File _file = new File(targetImg);
      Image src = ImageIO.read(_file);
      int wideth = src.getWidth(null);
      int height = src.getHeight(null);
      BufferedImage image = new BufferedImage(wideth, height, 1);
      Graphics g = image.createGraphics();
      g.drawImage(src, 0, 0, wideth, height, null);
      g.setColor(Color.RED);
      g.setFont(new Font(fontName, fontStyle, fontSize));
      g.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
      g.dispose();
      return image;
    } catch (Exception e) {
    	logger.debug(e);
    }
    return null;
  }

  public static BufferedImage thumbnail(String target, float ratio)
  {
    try
    {
      File input = new File(target);
      BufferedImage imageOriginal = ImageIO.read(input);
      if (imageOriginal == null) {
        imageOriginal = (BufferedImage)bmpReader(input);
      }
      if ((ratio == 1.0F) || (ratio == 0.0F)) {
        return imageOriginal;
      }
      int realWidth = imageOriginal.getWidth();
      int realHeight = imageOriginal.getHeight();

      int standardWidth = (int)(realWidth * ratio);
      int standardHeight = (int)(realHeight * ratio);

      return thumbnail(imageOriginal, realWidth, realHeight, standardWidth, standardHeight);
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }return null;
  }

  public static BufferedImage thumbnail(String target, int width, int heigth)
  {
    try
    {
      File input = new File(target);
      BufferedImage imageOriginal = ImageIO.read(input);
      if (imageOriginal == null) {
        imageOriginal = (BufferedImage)bmpReader(input);
      }
      int realWidth = imageOriginal.getWidth();
      int realHeight = imageOriginal.getHeight();
      if ((realWidth == width) && (realHeight == heigth))
        return imageOriginal;
      int standardWidth = width;
      int standardHeight = heigth;
      return thumbnail(imageOriginal, realWidth, realHeight, standardWidth, standardHeight);
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }return null;
  }

  public static BufferedImage thumbnail(BufferedImage imageOriginal, int realWidth, int realHeight, int width, int heigth)
  {
    try {
      int newWidth = 0; int newHeight = 0;
      if ((realWidth > width) && (width > 0)) {
        newWidth = width;
        newHeight = realHeight * newWidth / realWidth;
      } else if ((realHeight > heigth) && (heigth > 0)) {
        newHeight = heigth;
        newWidth = realWidth * newHeight / realHeight;
      }
      Image image = imageOriginal.getScaledInstance(newWidth, newHeight, 1);
      BufferedImage tag = new BufferedImage(newWidth, newHeight, 1);
      Graphics g = tag.getGraphics();
      g.drawImage(image, 0, 0, null);
      g.dispose();
      return tag;
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }
    return imageOriginal;
  }

//  public static void main(String[] args)
//  {
//    try
//    {
//      BufferedImage image = pressImage("E:/workspace/fantasy/WebContent/logo.gif", "E:/workspace/fantasy/WebContent/ditie.jpeg", 20, 20);
//
//      image = pressImage("E:/workspace/fantasy/WebContent/logo.gif", image, 130, 120);
//      write(image, "E:/workspace/fantasy/WebContent/F1214.jpg");
//      image = thumbnail("E:/workspace/Blog/WebContent/1214.jpg", 0.59F);
//    }
//    catch (Exception e)
//    {
//      log.error(e.getMessage());
//    }
//  }

  public static Image bmpReader(File file) {
    Image image = null;
    try {
      FileInputStream fs = new FileInputStream(file);
      int bflen = 14;
      byte[] bf = new byte[bflen];
      fs.read(bf, 0, bflen);
      int bilen = 40;
      byte[] bi = new byte[bilen];
      fs.read(bi, 0, bilen);

      int nwidth = (bi[7] & 0xFF) << 24 | (bi[6] & 0xFF) << 16 | (bi[5] & 0xFF) << 8 | bi[4] & 0xFF;
      logger.debug("width: " + nwidth);
      int nheight = (bi[11] & 0xFF) << 24 | (bi[10] & 0xFF) << 16 | (bi[9] & 0xFF) << 8 | bi[8] & 0xFF;
      logger.debug("height: " + nheight);

      int nbitcount = (bi[15] & 0xFF) << 8 | bi[14] & 0xFF;
      logger.debug("bits: " + nbitcount);

      int nsizeimage = (bi[23] & 0xFF) << 24 | (bi[22] & 0xFF) << 16 | (bi[21] & 0xFF) << 8 | bi[20] & 0xFF;
      logger.debug("source size: " + nsizeimage);

      if (nbitcount == 24) {
        int npad = nsizeimage / nheight - nwidth * 3;
        int[] ndata = new int[nheight * nwidth];
        byte[] brgb = new byte[(nwidth + npad) * 3 * nheight];
        fs.read(brgb, 0, (nwidth + npad) * 3 * nheight);
        int nindex = 0;
        for (int j = 0; j < nheight; j++) {
          for (int i = 0; i < nwidth; i++) {
            ndata[(nwidth * (nheight - j - 1) + i)] = 
              (0xFF000000 | 
              (brgb[(nindex + 2)] & 0xFF) << 16 | 
              (brgb[(nindex + 1)] & 0xFF) << 8 | 
              brgb[nindex] & 0xFF);
            nindex += 3;
          }
          nindex += npad;
        }
        Toolkit kit = Toolkit.getDefaultToolkit();
        image = kit.createImage(new MemoryImageSource(nwidth, nheight, ndata, 0, nwidth));
        logger.debug("read bmp image success");
      } else {
        logger.debug("it's not 24bits bmp, fail.");
        Toolkit kit = Toolkit.getDefaultToolkit();

        URL url = new URL("file:///E:/workspace/fantasy/WebContent/21286.jpg");
        logger.debug("file:///E:/workspace/fantasy/WebContent/21286.jpg");
        image = kit.getImage(url);
      }
      fs.close();
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }
    return image;
  }

  public static BufferedImage screenshots(String input, int x, int y, int w, int h, float ratio)
  {
    return screenshots(thumbnail(input, ratio), x, y, w, h);
  }

  public static BufferedImage screenshots(String input, int x, int y, int w, int h)
  {
    try
    {
      return screenshots(thumbnail(input, 1.0F), x, y, w, h);
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }
    return null;
  }

  public static BufferedImage screenshots(BufferedImage img, int x, int y, int w, int h)
  {
    try
    {
      if (logger.isDebugEnabled())
        logger.debug("Method:screenshots,param:{x:" + x + ",y:" + y + ",w:" + w + ",h:" + h + "}");
      ImageFilter cropFilter = new CropImageFilter(x, y, w, h);
      Image newImg = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), cropFilter));
      BufferedImage tag = new BufferedImage(w, h, 1);
      Graphics g = tag.getGraphics();
      g.drawImage(newImg, 0, 0, null);
      g.dispose();
      return tag;
    } catch (Exception e) {
    	logger.error(e.getMessage());
    }
    return null;
  }

  public String getCheckCodeImage(String str, int show, ByteArrayOutputStream output)
  {
    Random random = new Random();
    BufferedImage image = new BufferedImage(80, 30, 5);
    Font font = new Font("Arial", 0, 24);
    int distance = 18;
    Graphics d = image.getGraphics();
    d.setColor(Color.WHITE);
    d.fillRect(0, 0, image.getWidth(), image.getHeight());
    d.setColor(new Color(random.nextInt(100) + 100, random.nextInt(100) + 100, random.nextInt(100) + 100));
    for (int i = 0; i < 10; i++) {
      d.drawLine(random.nextInt(image.getWidth()), random.nextInt(image.getHeight()), random.nextInt(image.getWidth()), 
        random.nextInt(image.getHeight()));
    }
    d.setColor(Color.BLACK);
    d.setFont(font);
    String checkCode = "";

    int x = -distance;
    for (int i = 0; i < show; i++) {
      char tmp = str.charAt(random.nextInt(str.length() - 1));
      checkCode = checkCode + tmp;
      x += distance;
      d.setColor(new Color(random.nextInt(100) + 50, random.nextInt(100) + 50, random.nextInt(100) + 50));
      d.drawString(tmp+"", x, random.nextInt(image.getHeight() - font.getSize()) + font.getSize());
    }
    d.dispose();
    try {
      ImageIO.write(image, "jpg", output);
    } catch (IOException e) {
      logger.warn("生成验证码错误.", e);
    }
    return checkCode;
  }

  public static BufferedImage getBarcode(int width, int height, char[] codes) {
    BufferedImage image = new BufferedImage(width, height, 1);

    Graphics g = image.getGraphics();

    Random random = new Random();

    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);

    g.setFont(new Font("Courier New", 1, 18));

    g.setColor(new Color(height));
    g.drawRect(0, 0, width - 1, height - 1);

    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
      int x = random.nextInt(width - 1);
      int y = random.nextInt(height - 1);
      int xl = random.nextInt(6) + 1;
      int yl = random.nextInt(12) + 1;
      g.drawLine(x, y, x + xl, y + yl);
    }

    for (int i = 0; i < 70; i++) {
      int x = random.nextInt(width - 1);
      int y = random.nextInt(height - 1);
      int xl = random.nextInt(12) + 1;
      int yl = random.nextInt(6) + 1;
      g.drawLine(x, y, x - xl, y - yl);
    }

    for (int i = 0; i < codes.length; i++)
    {
      g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      g.drawString(String.valueOf(codes[i]), 15 * i + 10, 15);
    }

    g.dispose();
    return image;
  }

  private static Color getRandColor(int fc, int bc)
  {
    Random random = new Random();
    if (fc > 255) fc = 255;
    if (bc > 255) bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
}
