package com.valkryst.VTerminal.samples.printer;

import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.PanelBuilder;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;
import com.valkryst.VTerminal.printer.ImagePrinter;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class SampleImagePrinter {
    public static void main(final String[] args) throws IOException, URISyntaxException, InterruptedException {
        final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/20pt/bitmap.png",
                                                    "Fonts/DejaVu Sans Mono/20pt/data.fnt",
                                                              1);

        final PanelBuilder builder = new PanelBuilder();
        builder.setFont(font);
        final Panel panel = builder.build();

        Thread.sleep(50);

        final String filePath = System.getProperty("user.dir") + "/res_test/ImagePrinterTest.png";
        final BufferedImage image = ImageIO.read(new File(filePath));

        final ImagePrinter printer = new ImagePrinter(image);
        printer.print(panel, new Point(0, 0));

        panel.draw();
    }
}