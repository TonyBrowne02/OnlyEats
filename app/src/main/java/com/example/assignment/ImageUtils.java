    package com.example.assignment;

    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.util.Base64;
    import java.io.ByteArrayOutputStream;
    import java.util.zip.Deflater;
    import java.util.zip.Inflater;

    public class ImageUtils {

        // Convert Bitmap to compressed byte array
        public static byte[] compressBitmap(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return compressByteArray(stream.toByteArray());
        }

        // Compress byte array using Deflater
        public static byte[] compressByteArray(byte[] input) {
            Deflater deflater = new Deflater();
            deflater.setInput(input);
            deflater.finish();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }

            return outputStream.toByteArray();
        }

        // Decompress byte array using Inflater
        public static byte[] decompressByteArray(byte[] input) {
            Inflater inflater = new Inflater();
            inflater.setInput(input);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                try {
                    int count = inflater.inflate(buffer);
                    outputStream.write(buffer, 0, count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return outputStream.toByteArray();
        }

        // Convert compressed byte array to Bitmap
        public static Bitmap decompressBitmap(byte[] compressedData) {
            byte[] decompressedData = decompressByteArray(compressedData);
            return BitmapFactory.decodeByteArray(decompressedData, 0, decompressedData.length);
        }

        // Convert byte array to Base64 string
        public static String byteArrayToBase64(byte[] byteArray) {
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        // Convert Base64 string to byte array
        public static byte[] base64ToByteArray(String base64String) {
            return Base64.decode(base64String, Base64.DEFAULT);
        }
    }

