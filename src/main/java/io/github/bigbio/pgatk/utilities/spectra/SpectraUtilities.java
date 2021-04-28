package io.github.bigbio.pgatk.utilities.spectra;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class SpectraUtilities {

  public static final int BYTES_64_PRECISION = 8;

  public static byte[] encodeBinary(List<Double> values) {
    double[] arrValues = new double[values.size()];
    for(int i = 0; i < values.size(); i++)
      arrValues[i] = values.get(i);

    ByteBuffer buffer = ByteBuffer.allocate(arrValues.length * BYTES_64_PRECISION);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    for (double aDoubleArray : arrValues) {
      buffer.putDouble(aDoubleArray);
    }
    byte[] data = compress(buffer.array());
    return data;
//    return Base64.getEncoder().encodeToString(data);
  }

  /**
   * Compressed source data using the Deflate algorithm.
   * @param uncompressedData Data to be compressed
   * @return Compressed data
   */
  public static byte[] compress(byte[] uncompressedData) {
    byte[] data; // Decompress the data

    // create a temporary byte array big enough to hold the compressed data
    // with the worst compression (the length of the initial (uncompressed) data)
    // EDIT: if it turns out this byte array was not big enough, then double its size and try again.
    byte[] temp = new byte[uncompressedData.length / 2];
    int compressedBytes = temp.length;
    while (compressedBytes == temp.length) {
      // compress
      temp = new byte[temp.length * 2];
      Deflater compresser = new Deflater();
      compresser.setInput(uncompressedData);
      compresser.finish();
      compressedBytes = compresser.deflate(temp);
    }

    // create a new array with the size of the compressed data (compressedBytes)
    data = new byte[compressedBytes];
    System.arraycopy(temp, 0, data, 0, compressedBytes);

    return data;
  }

  public static byte[] decompress(byte[] compressedData) {
    byte[] decompressedData;

    // using a ByteArrayOutputStream to not having to define the result array size beforehand
    Inflater decompressor = new Inflater();

    decompressor.setInput(compressedData);
    // Create an expandable byte array to hold the decompressed data
    ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);
    byte[] buf = new byte[1024];
    while (!decompressor.finished()) {
      try {
        int count = decompressor.inflate(buf);
        if (count == 0 && decompressor.needsInput()) {
          break;
        }
        bos.write(buf, 0, count);
      } catch (DataFormatException e) {
        throw new IllegalStateException("Encountered wrong data format " +
          "while trying to decompress binary data!", e);
      }
    }
    try {
      bos.close();
    } catch (IOException e) {
      // ToDo: add logging
      e.printStackTrace();
    }
    // Get the decompressed data
    decompressedData = bos.toByteArray();

    if (decompressedData == null) {
      throw new IllegalStateException("Decompression of binary data produced no result (null)!");
    }
    return decompressedData;
  }

  public static Number[] convertData(byte[] data) {
    int step = BYTES_64_PRECISION;
    // create a Number array of sufficient size
    Number[] resultArray = new Number[data.length / step];
    // create a buffer around the data array for easier retrieval
    ByteBuffer bb = ByteBuffer.wrap(data);
    bb.order(ByteOrder.LITTLE_ENDIAN); // the order is always LITTLE_ENDIAN
    // progress in steps of 4/8 bytes according to the set step
    for (int indexOut = 0; indexOut < data.length; indexOut += step) {
      // Note that the 'getFloat(index)' and getInt(index) methods read the next 4 bytes
      // and the 'getDouble(index)' and getLong(index) methods read the next 8 bytes.
      Number num = bb.getDouble(indexOut);
      resultArray[indexOut / step] = num;
    }
    return resultArray;
  }

  public static List<Double> decodeBinary(byte[] decode){
    // base64 encoded string to byte[]

//    byte[] decode = Base64.getDecoder().decode(valueString);
    byte[] originalValues = decompress(decode);
    Number[] values = convertData(originalValues);

    List<Double> resultValues = new ArrayList<>(values.length);
    for(int i =0; i < values.length; i++)
      resultValues.add(values[i].doubleValue());

    return resultValues;
  }

}
