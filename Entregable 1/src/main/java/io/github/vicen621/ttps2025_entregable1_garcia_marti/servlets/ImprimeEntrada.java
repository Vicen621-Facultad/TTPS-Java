package io.github.vicen621.ttps2025_entregable1_garcia_marti.servlets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(name = "imprimeEntrada", value = "/imprime_entrada")
public class ImprimeEntrada extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        String pelicula = request.getParameter("pelicula");

        OutputStream out = response.getOutputStream();
        BufferedImage entrada = ImageIO.read(this.getServletContext().getResourceAsStream("/WEB-INF/cupon.jpeg"));
        BufferedImage image = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(entrada, 0, 0, null, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(nombre + " " + apellidos, 80, 266);
        g.drawString("DNI: " + dni, 80, 306);

        try {
            BufferedImage qr = generateQR(pelicula, nombre, apellidos, dni);
            g.drawImage(qr, 500, 193, null, null);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        ImageIO.write(image, "png", out);
        out.close();
    }

    private BufferedImage generateQR(String pelicula, String nombre, String apellidos, String dni) throws WriterException {
        ServletContext context = getServletContext();
        int cantidadLatas = (int) context.getAttribute("cantidad_latas");
        if (cantidadLatas > 0 && new Random().nextDouble() < 0.05) { // 5% de probabilidad
            context.setAttribute("cantidad_latas", cantidadLatas - 1);
            return generateQR(
                    String.format("""
                        Entrada para la película %s.
                        ¡¡Felicitaciones!!
                        %s %s, DNI: %s
                        Te ganaste una LATA DE POCHOCLOS. Podes retirarla con esta entrada""",
                    pelicula, nombre, apellidos, dni)
            );
        } else {
            return generateQR(
                    String.format("""
                        Entrada para la película %s.
                        %s %s, DNI: %s
                        ¡Seguí viniendo al CINE!""",
                    pelicula, nombre, apellidos, dni)
            );
        }
    }

    private BufferedImage generateQR(String texto) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, 150, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}