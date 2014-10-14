package com.component;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;

public class PicUtil {

	private int brighter;

	public int getBrighter() {
		return brighter;
	}

	public void setBrighter(int brighter) {
		this.brighter = brighter;
	}

	public final BufferedImage getPicture(BufferedImage originalPic) {
		int imageWidth = originalPic.getWidth();
		int imageHeight = originalPic.getHeight();

		BufferedImage newPic = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_3BYTE_BGR);

		short[] brighten = new short[256];
		short pixelValue;

		for (int i = 0; i < 256; i++) {
			pixelValue = (short) (i + brighter);
			if (pixelValue > 255) {
				pixelValue = 255;
			}
			brighten[i] = pixelValue;
		}

		LookupTable lut = new ShortLookupTable(0, brighten);
		LookupOp lop = new LookupOp(lut, null);
		lop.filter(originalPic, newPic);
		return newPic;
	}

	public final BufferedImage getGrayPicture(BufferedImage originalPic) {
		int imageWidth = originalPic.getWidth();
		int imageHeight = originalPic.getHeight();

		BufferedImage newPic = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_BYTE_GRAY);

		ColorConvertOp cco = new ColorConvertOp(ColorSpace
				.getInstance(ColorSpace.CS_GRAY), null);
		cco.filter(originalPic, newPic);
		return newPic;
	}
}
