package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.avro.*;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.*;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Encoder;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.Schema;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONtoAvro {
	
	 public static void main(final String[] args) {
	        try {
	        	
	        	String fileDir ="C:\\Users\\compagnoneda\\Desktop\\AVRO KAFKA ENERGICA";
	            System.out.println("java -jar J2A [message.json] [avroschema.json]");
	            String fileIn = "C:\\Users\\compagnoneda\\Desktop\\bin";
	            final String schema = readFile(fileDir+"\\diagnostic_avro_schema.json");
	            
	        	ArrayList<String> listfiles = Main.listFiles(Main.apriFile(fileIn));
	        	for (String s : listfiles ) {
	        		
	        	
	            final String message = readFile(fileIn+"\\"+s);
	            final FileOutputStream fos = new FileOutputStream(fileIn  + "\\Out_avro\\"+s+".avro");
	            final byte[] avro = fromJsonToAvro(message, schema);
	          
	          
	            
	            fos.write(avro);
	            fos.close();
	            System.out.println();
	        	}
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	    
	    static String readFile(final String path) throws IOException {
	        final byte[] encoded = Files.readAllBytes(Paths.get(path, new String[0]));
	        return new String(encoded, StandardCharsets.US_ASCII);
	    }
	    
	    static byte[] fromJsonToAvro(final String json, final String schemastr) throws Exception {
	        final InputStream input = new ByteArrayInputStream(json.getBytes());
	        final DataInputStream din = new DataInputStream(input);
	        final Schema schema = Schema.parse(schemastr);
	        final Decoder decoder = (Decoder)DecoderFactory.get().jsonDecoder(schema, (InputStream)din);
	        final DatumReader<Object> reader = (DatumReader<Object>)new GenericDatumReader(schema);
	        final Object datum = reader.read((Object)null, decoder);
	        final GenericDatumWriter<Object> w = (GenericDatumWriter<Object>)new GenericDatumWriter(schema);
	        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        final Encoder e = (Encoder)EncoderFactory.get().binaryEncoder((OutputStream)outputStream, (BinaryEncoder)null);
	        w.write(datum, e);
	        e.flush();
	        return outputStream.toByteArray();
	    }
	    
	    
	   
}
