package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

import com.google.gson.Gson;

import beens.Diagnostica;
import beens.Metrica;
import beens.StatisticMetrica;
import dao.DAO;
import dao.DiagnosticListDAO;
import dao.MetricaDAO;
import dao.RevisionDAO;
import dao.StatMetricaDAO;
import dao.StatistcMetricaDAO;

public class Main {

	private static final Object[] String = null;
	private static final java.lang.String EVM_PORTAL = null;
	private static final java.lang.String KAFKA = "KAFKA";

	public static void main(String[] args) {
	
		//----------------------
				String incrementoFile="_152";
				String VIN="ZNNV1A10000000001";
				String note="nuovo giro timesynch ="+ incrementoFile;
		//-----------------
				
		String fileDir = "C:\\Users\\compagnoneda\\Desktop\\bin";
		String fileIn = "C:\\Users\\compagnoneda\\Desktop\\bin\\In\\KafkaIn\\In";
		String fileAvro = "C:\\Users\\compagnoneda\\Desktop\\bin\\Out\\avro\\energica.avsc";
		String confPath = fileDir+"\\Out\\diagnosticListComplete.txt";
		String filePath = fileDir+"\\Out\\report"+incrementoFile+".csv";
		String fileDir_1 ="C:\\Users\\compagnoneda\\Desktop\\AVRO KAFKA ENERGICA";
		String jsonPath = fileDir.concat("\\Out\\output").concat(incrementoFile).concat(".json");
		String fileEVM = "Diagnostiche - "+VIN+".csv";
		String pathEVMfile = fileDir+"\\In\\EvmIn\\"+fileEVM;
		final String KAFKA = "KAFKA";
		final String EVM_PORTAL= "EVM";
	
		
		

	kafkaAnalisys( fileIn, fileAvro,  jsonPath,  note,  filePath);
//	omooveAnalisys( note,  pathEVMfile,  VIN,  filePath);
//	inserisciDiagnosticKeyComplete(confPath);
		
	
//	-------------------------------------------------------------------------------------
	
//		convertAvroToJson( fileIn,  fileAvro, jsonPath);
//		inserisciDiagnosticKeyComplete(confPath);

//		//decodifica
//		Integer incrementoJson = convertAvroToJson(fileIn ,fileAvro, jsonPath);
//////		avvioDecodificaBin(fileIn, incrementoFile);
//////
////////		//inserire le metriche di diagnostica su db 
//		Integer revision = insertMetricheList(deserializzaJSON(jsonPath));
////		
//		Integer incrementoJson = 1;
		
		//per KAFKA
//		Integer revision= createLastRevision(note);
//		for (int i=0 ; i<=incrementoJson ; i++) {
//			Integer index = i;
//			insertMetricheListWithRevision(  deserializzaJSON(jsonPath.concat("_").concat(index.toString())) , revision, KAFKA);
//		}
////		
		//inserimento metriche EVM
//		Integer revision= createLastRevision(note);
//		
//		for(Metrica m : deserializzaCSVEVM(pathEVMfile,  VIN)) {
//			//stampa
//			System.out.println(m.getDiagnosticType()+" "+m.getDate()+" "+m.getTime());
//		};
//			insertMetricheListWithRevision(deserializzaCSVEVM(pathEVMfile,  VIN) , revision, EVM_PORTAL);
		
////		
////		
////		
////////		//calcolo  delle statistiche
////		inserisciDiagnosticKey(confPath);
//		calcolaStatistiche(103,filePath,EVM_PORTAL);
//		calcolaStatisticheNew(revision,filePath,KAFKA);
//		calcolaStatisticheNew(revision,filePath,EVM_PORTAL);

		
		//------------------------------------------------------------
		
//		deserializzaJSON(jsonPath);
		
//		Integer revision = insertMetriche(deserializzaJSON(fileDir.concat("\\Out\\output"+incrementoFile+".json")));

		//effettau la decodificada l bin e crea il file json e di testo
//		avvioDecodificaBin(fileDir, incrementoFile);
//		// da qui serve il DB locale
//		inserisciDiagnosticKey(confPath);
		
		
		////////////////////////////////////////////////
//		avvioDecodificaBin(fileDir, incrementoFile);
		//Salvataggio metriche 
//		String fileCSV = salvaMetricaAsCSV(deserializzaJSON(fileDir.concat("\\Out\\output"+incrementoFile+".json")));
//		File outCSV = newFile(fileDir.concat("\\Out\\metriche"+incrementoFile+".csv")); 
//		scriviFile(fileDir.concat("\\Out\\metriche"+incrementoFile+".csv"),  fileCSV);
		
	// fa il calcolo di quante diagnostiche ci sono su un json estratto 
//		String testo = leggiFile(fileDir.concat("\\Out\\output_5.json"));
//		verificaDiagnostiche(testo, confPath);
		

		
		//stampa a video le metriche da un JSON dopo averle deserializzate -> per test
//		stampaMetrica(deserializzaJSON(fileDir.concat("\\Out\\output_6.json")));

		//crea il file csv delle metriche da un JSON dopo averle deserializzate -> per essere verificato su Excel
//		String fileCSV = salvaMetricaAsCSV(deserializzaJSON(fileDir.concat("\\Out\\output_5.json")));
//		//System.out.print(fileCSV);
//		File outCSV = newFile(fileDir.concat("\\Out\\metriche_5.csv")); 
//		scriviFile(fileDir.concat("\\Out\\metriche_5.csv"),  fileCSV);
		 
		
		

		
	}
	
	public static void kafkaAnalisys(java.lang.String fileIn, java.lang.String fileAvro, java.lang.String jsonPath, java.lang.String note, java.lang.String filePath) {
//		//decodifica
		Integer incrementoJson = convertAvroToJson(fileIn ,fileAvro, jsonPath);
		Integer revision= createLastRevision(note);
		for (int i=0 ; i<=incrementoJson ; i++) {
			Integer index = i;
			insertMetricheListWithRevision(  deserializzaJSON(jsonPath.concat("_").concat(index.toString())) , revision, KAFKA);
		}
		calcolaStatisticheNew(revision,filePath,KAFKA);
	}
	
	public static void omooveAnalisys(java.lang.String note, java.lang.String pathEVMfile, java.lang.String VIN, java.lang.String filePath) {
		//inserimento metriche EVM
		Integer revision= createLastRevision(note);
		for(Metrica m : deserializzaCSVEVM(pathEVMfile,  VIN)) {
			//stampa
			System.out.println(m.getDiagnosticType()+" "+m.getDate()+" "+m.getTime());
		};
		insertMetricheListWithRevision(deserializzaCSVEVM(pathEVMfile,  VIN) , revision, EVM_PORTAL);
		calcolaStatisticheNew(revision,filePath,EVM_PORTAL);
		
	}
	
	
	

	public static void avvioDecodificaBin(String path, String incremento) {
		
		String filePath = "";
		String fileDir = path;
		String incrementoFile=incremento;
		String riga = "";
		String fileJson = "";
		String fileTesto="";
		ArrayList<String> files;
		ArrayList<String> fileCostruzione = new ArrayList<String> ();
		
		//aprire la directory dei bin
				//prendere la lista dei file nella directory
				files = listFiles(apriFile(fileDir));
				String jsonPath = fileDir.concat("\\Out\\output").concat(incrementoFile).concat(".json");
				String txtPath = fileDir.concat("\\Out\\output".concat(incrementoFile).concat(".txt"));
				File jsonFile = newFile(jsonPath);
				File txtFile = newFile(txtPath);
				
				Integer index=0;
				for (  String fileName  : files){
							
				//leggere il singolo file 
				//codificare il byte in asci
				//costruire la stringa di output
				filePath=fileDir.concat("//").concat(fileName);
				riga=read(filePath);
				
				
//				test conversione
//				int a=101;
//				System.out.print(converti(a));
				
			
				
				//inserire la stringa nel file di output
				fileJson+=riga.concat("\n");
				//INSERIRE INDICE DI AVANZAMENTO <------------------------------------------------------------
				index++;
				System.out.println("index: "+index+" "+riga);
				//fileTesto+=formatta(riga).concat("\n");
				//chiususra del for each
				if(index%5000==0) {
					fileCostruzione.add(fileJson);
					fileJson="";
				}
//				if(index%5000==0) {
//					scriviFile(jsonPath, fileJson);
//					scriviFile(txtPath,fileTesto);
//					fileJson="";
//					fileTesto="";
//				
//				}
				}
				fileCostruzione.add(fileJson);
				
				
//				System.out.println(fileJson);
				
				//salvare e chiudere il file di output
				
				String testoJSON= "";
				for(String s : fileCostruzione) {
					testoJSON+=s;
				}
				
				//per l'ultima scrittura
				scriviFile(jsonPath, testoJSON);
				//scriviFile(txtPath,fileTesto);
		
	}




	public static String read(String filePath){
		
		String riga = "";
	  try {
	    FileInputStream fis = new FileInputStream(new File(filePath));
	
	    int c = fis.read();
	
	    while (c != -1){
	      
	      if(c!=-1) {
	    	  riga+=converti(c);
	    	  	      }
	      c = fis.read();
	      
	    }
	
	    fis.close();
	  }
	  catch (FileNotFoundException e) {
	    e.printStackTrace();
	  }
	  catch (IOException e) {
	    e.printStackTrace();
	  }
	  
	  return riga;
	}
	
	public static String converti(int c) {
		
		return Character.toString ((char) c);

			}
	
	public static String formatta(String riga) {
		String riga1 = riga.replace(",", ",\n");
		String riga2 = riga1.replace("}", "} \n");
		return riga2;
	}
	
	public static ArrayList<String> listFiles(final File folder) {
		
		ArrayList<String> list = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFiles(fileEntry);
	        } else {
	            //System.out.println(fileEntry.getName());
	            list.add(fileEntry.getName());
	        }
	    }
	    return list;
	}

	public static File apriFile(String path) {
		File file = new File (path);
		return file;
		
	}

	public static File newFile(String path) {
		
		try {
		File file = new File(path);
		
		if (file.exists())
		System.out.println("Il file " + path + " esiste");
		else if (file.createNewFile()) {
		System.out.println("Il file " + path + " � stato creato");
		return file;
		}
		else
		System.out.println("Il file " + path + " non pu� essere creato");


		} catch (IOException e) {
		e.printStackTrace();
		}
		
		return null;
		
		}

	public	static void  scriviFile(String fileName,  String contenuto){
		  
		    FileWriter w;
		    try {
				w=new FileWriter(fileName);
			
			
		    BufferedWriter b;
		    b=new BufferedWriter (w);

			b.write(contenuto);
			
		    b.flush();
		    
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	public static ArrayList<Metrica> deserializza(String jsonFile){
		
		
		return null;
	}

	public static String leggiFile(String filePath) {
		
		String test="";
		FileReader f;
	    try {
			f=new FileReader(filePath);
		

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s;
	    

	    while(true) {
	      s=b.readLine();
	      
	      if(s==null)
	        break;
	      else{test+=s;}
//	      System.out.println(s);
	     
	    }
	    
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    	System.out.println(test);
			return test;
	}

	public static int contaOccorrenze(String testo, String parola) {
		
		
		 
		  int lunghezza_parola = parola.length();
		  int lunghezza_testo = testo.length();
		  // Definisco due variabili: un indice per il testo e un contatore
		  int indice = 0;
		  int numeroParole = 0;
		  // Fin quando l'indice del testo � valido
		  while (indice <= lunghezza_testo-lunghezza_parola){
		      // Controllo se la substring del testo � uguale alla parola
		      if(testo.subSequence(indice, indice+lunghezza_parola).equals(parola)){
		          numeroParole++;
		      }
		      // L'indice aumenta ad ogni iterazione
		      indice++;
		  }
		  
		  return numeroParole;
	}

	public static void verificaDiagnostiche(String testo , String confPath) {
		
		System.out.println("------eseguo il conteggio delle diagnostiche--------");
		ArrayList<String> diagnosticList = new ArrayList<String> ();
		diagnosticList = leggiRigheFile(confPath);
		int occorrezeTotale = 0;
		
		for(String diagnosticaType : diagnosticList) {
			System.out.print(diagnosticaType	+ ":" );
//			int occorrenza = contaOccorrenze(testo, diagnosticaType);
			int occorrenza = countStringInString( diagnosticaType, testo);
			System.out.println(occorrenza);
			occorrezeTotale+=occorrenza;
		}
		System.out.print("Numero occorrenze Totali: ");
		System.out.println(occorrezeTotale);
		
		
	}
	
	public static ArrayList<String> leggiRigheFile(String filePath) {
		
		ArrayList<String> righe = new ArrayList<String> ();
		
		FileReader f;
	    try {
			f=new FileReader(filePath);
		

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s;
	    

	    while(true) {
	      s=b.readLine();
	    
	      if(s==null)
	        break;
	      righe.add(s);
//	      System.out.println(s);
	     
	    }
	    
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    	System.out.println(test);
			return righe;
	}

	public static int countStringInString(String search, String text) {
//		System.out.print(search+" : ");
		
	    Pattern pattern = Pattern.compile(search);
	    Matcher matcher = pattern.matcher(text);
	    
	    
	    int stringOccurrences = 0;
	    while (matcher.find()) {
	      stringOccurrences++;
//	      System.out.print(stringOccurrences);
	    }
	    return stringOccurrences;
	}

	public static  ArrayList<Metrica> deserializzaJSON(String filePath){
		
		 
				 
				 
		ArrayList<Metrica> list = new ArrayList<>();
		ArrayList<String> fallimenti = new ArrayList<>();
		Integer index=0;
		
		FileReader f;
	    try {
			f=new FileReader(filePath);
		

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s;
	    

	    while(true) {
	      s=b.readLine();
	      
	      if(s==null)
	        break;
	      else{
	    	 
	    	  
	    		if(s.startsWith("{")) {  
	    	  Metrica parse = new Gson().fromJson(s, Metrica.class);
	    	  parse.setDateTime();
	    	  index++;
	    	  System.out.println("Deserializzazione e aggiunta alla lista - index :"+index);
	    	  //System.out.println(parse.toCSV());
	    	  list.add(parse);
	    		}else {
	    			index++;
	    			System.out.println("Deserializzazione fallita - index :"+index);
	    			fallimenti.add(("  - index :"+index + s));
	    		}
	      }
	      }
	    }catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	        // Show it.
	    for(String s: fallimenti) {
	    	  System.out.println("Deserializzazione fallimenti :"+s);
	    }
	       
		return list;
		
	}
	
	
	public static  ArrayList<Metrica> deserializzaCSVEVM(String filePath, String VIN){
		
		 
		 
		 
		ArrayList<Metrica> list = new ArrayList<>();
		ArrayList<String> fallimenti = new ArrayList<>();
		Integer index=0;
		
		FileReader f;
	    try {
			f=new FileReader(filePath);
		

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s;
	    int i=0;
	    
	    s=b.readLine();
	    

	    while(true) {
	      s=b.readLine();
	      
	      if(s==null)
	        break;
	      else{
	    	 //rimuovo gli "
	    	  s=s.replaceAll("\"", "");
	    	  //split string nei suoi valori
	    	  String[] values = s.split(",");
//	    	  for (String s1 : values) {
//	    		  System.out.println(s1);
//	    	  }
	    	  //inserisco i valori nel  con il VIN   in un oggetto metrica
	    	  // TO-DO : popolare anche voucher Id e altro eventuale
	    	  Metrica metrica = new Metrica();
	    	  metrica.setDiagnosticKey(values[0]);
	    	  metrica.setDiagnosticType(values[0]);
	    	  metrica.setDate(values[1]);
	    	  metrica.setTime(values[2].replace(" ", ""));
	    	  //split del valore dall'unit� di misura
	    	  String misura = "";
	    	  String unita = "";
	    	  
	    	  if(values[3].contains(" ")) {
	    		   misura = (String) values[3].subSequence(0, values[3].indexOf(" ")); 
		    	   unita = (String) values[3].subSequence(values[3].indexOf(" ")+1, values[3].length()); 
	    	  }
	    	  else {
	    		   misura =  values[3] ;
	    		  
	    	  }
	    	  
//	    	  System.out.println(misura);
//	    	  System.out.println(unit�);
//	    	  System.out.println("----------------");
//	    	  
	    	  metrica.setValue(misura);
	    	  metrica.setUnit(unita);
	    	  System.out.print(i++);
	    	  System.out.println(" "+s);
	    	  if(values.length<=5) metrica.setPolicy("");
	    	  else metrica.setPolicy(values[5]);
	    	  
	    	  metrica.setVin(VIN);
	    	  
	    	  
	    	  
	    	  // aggiungo alla lista delle metriche
	    	
	    	 // System.out.println("Formattazione  e aggiunta alla lista - index :"+index++);
	    	  
	    	  list.add(metrica);
	    	
	      }
	    }
	    }catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	        // Show it.
	    for(String s: fallimenti) {
	    	  System.out.println("Deserializzazione fallimenti :"+s);
	    }
	       
		return list;
		
	}

	public static void stampaMetrica(ArrayList<Metrica> list) {
		
		//System.out.println(list.isEmpty());
		for (Metrica metrica : list ) {
			System.out.println(metrica.toCSV());
		}
		
	}
	
	public static String salvaMetricaAsCSV(ArrayList<Metrica> list) {
		
		String fileCSV="diagnosticKey;deviceId;voucherId;vin;timestamp;value;unit;policy;diagnosticType\n";
		ArrayList<String> CSV = new ArrayList<String>() ;
		Integer index=0;
			
		//System.out.println(list.isEmpty());
		for (Metrica metrica : list ) {
			System.out.println(metrica.toCSV());
			fileCSV+=metrica.toCSV()+"\n";
			index++;
			if(index%5000==0) {
				CSV.add(fileCSV);
				fileCSV="";
			}
		}
		CSV.add(fileCSV);
		fileCSV="";
		
		for(String s : CSV) {
			fileCSV+=s;
		}
		
		
		return fileCSV;
		
	}

	public static Connection connettiDB() {
		
		DAO dao = new DAO();
		return DAO.createConnection();
		
		
		
	}

	public static Integer maxDB() {
		
		MetricaDAO metricaDAO = new MetricaDAO();
		return metricaDAO.maxID();
	}

	public static Integer inserisciDiagnosticKeyComplete(String confPath) {
		
		DiagnosticListDAO diagnosticDAO =  new DiagnosticListDAO();
		Integer id =0;
		
		if(diagnosticDAO.deleteConf()==1) {
			ArrayList<String> list = leggiRigheFile(confPath);
			for(String s : list) {
				id++;
				float min=0;
				float max=0;
				//split della sringa 
				String[] values = s.split(";");
				Float scala =Float.parseFloat(values[2].replace(',', '.'));
				if(values[6].equals("boolean")) {
					//setta min e max booleano
					min=0;
					max=1;
				}else if(values[6].equals("code")) {
					//li prendo dalla stringa
					min= Float.parseFloat(values[4]);
					max= Float.parseFloat(values[5]);
				}else {
					//codifico in base al valore corrente
					char codificaS = values[1].charAt(0);
					int codificaN =0;
					if(values[1].length()>1) codificaN = Integer.parseInt(values[1].substring(1));
					
					switch(codificaS) {
					case 'U' :  min=0; max=(float) ((Math.pow(2,codificaN))-1)*scala; break;
					case 'S' :  min= (float) (Math.pow(2,codificaN-1))*(-1)*scala;max=(float) (Math.pow(2,codificaN-1)-1)*scala; break;
					case 'N' :  min= 0;	max = 0;
					}
					
					//correzzione per la percentuale
					if(values[3].equals("%")) {
						min = 0;
						max = 100f;
					}
					
				}
				
				
				if(diagnosticDAO.createConfCompleta(id,values[0],values[1],scala, values[3],min,max,values[6],values[7])>0) System.out.println("diagnostica "+values[0]+" configurata");
				else System.out.println("Errore alla configurazione "+values[0]);
				
			}
			
		}else System.out.println("Cancellazione Errata");
		
//		diagnosticDAO.createConf("ciao", 1);
		return 1;
		
	}

	public static Integer insertMetriche(ArrayList<Metrica> list,String note) {
		
		MetricaDAO metricaDAO = new MetricaDAO();
		RevisionDAO revisionDAO = new RevisionDAO();
		
		//creare la nuova revisione
		Integer id_revision = revisionDAO.createRevision(note);
		Integer index=0;
		
		if(id_revision>0) {
			//id da cui partire
			Integer id = metricaDAO.maxID();
			id++;
			for ( Metrica metrica : list) {
				Integer metricaId= metricaDAO.createMetrica(metrica, id, id_revision);
				//Inserire indice di avanzamento
				index++;
				System.out.println("Index:"+index+" - Inserita metrica con id : "+metricaId);
				id++;
			}
		}
		
		return id_revision;
		
		
	}
	
	
public static Integer insertMetricheList(ArrayList<Metrica> list,String note, String source) {
		
		MetricaDAO metricaDAO = new MetricaDAO();
		RevisionDAO revisionDAO = new RevisionDAO();
		
		//creare la nuova revisione
		Integer id_revision = revisionDAO.createRevision(note);
		Integer index=0;
		
		if(id_revision>0) {
			//id da cui partire
			Integer id = metricaDAO.maxID();
			id++;
			
			//effettuare la chiamata all'inserimento passando la lista completa
			metricaDAO.insertMetricList(list, id, id_revision,source);

		}
		
		return id_revision;
		
		
	}




public static Integer insertMetricheListWithRevision(ArrayList<Metrica> list , int id_revision, String source) {
	
	MetricaDAO metricaDAO = new MetricaDAO();
	RevisionDAO revisionDAO = new RevisionDAO();
	
	//creare la nuova revisione
	//Integer id_revision = revisionDAO.createRevision();
	Integer index=0;
	
	if(id_revision>0) {
		//id da cui partire
		Integer id = metricaDAO.maxID();
		id++;
		
		//effettuare la chiamata all'inserimento passando la lista completa
		metricaDAO.insertMetricList(list, id, id_revision,source);

	}
	
	return id_revision;
	
	
}



public static Integer createLastRevision(String note) {
	
	RevisionDAO revisionDAO = new RevisionDAO();
	
	//creare la nuova revisione
	Integer id_revision = revisionDAO.createRevision(note);
	
	return id_revision; 
}

	public static void calcolaStatistiche(Integer revision, String confPath, String filePath,String conf) {
		
		//lista delle diagnostiche da controllare
		//creare una lista di diagnosiche
		ArrayList<String> list = leggiRigheFile(confPath);
		//per ogni diagnostica richiedo i valori statistici calcolati
		
		String fileCSV="Calcololo delle Occorrenze \n \n Diagnostica; Numero di Occorrenze;Policy;DiagnosticType;Unit;Valore Minimo;Valore Massimo;Media dei valori \n";
		
		System.out.println("Calcolo delle occorrenze");
		System.out.println("----------------------------");
		System.out.println("Diagnostica \tNum Occorrenze \tPolicy \t \t \tDiagnosticType \t Unit \tMin \tMax \tMedia");
		for (String s : list) {
			System.out.print(s + "\t");
			fileCSV+=s+";";
			StatMetricaDAO stat = new StatMetricaDAO();
			//ritorna il numero di occorrenze
				Integer occ = stat.contaOccorrenze(s, revision);
				System.out.print(occ+"\t \t");
				fileCSV+=occ+";";
			//ritorna le policy
				String policy= stat.policy(s, revision);
				System.out.print(policy+"\t");
				fileCSV+=policy+";";
			//ritorna i diagnostictype
				String diaType=stat.diagnosticType(s, revision); 
				System.out.print(diaType+"\t");
				fileCSV+=diaType+";";
			
				//ritorna l'unit� di misura
				String unit=stat.unit(s, revision);
				System.out.print(unit+"\t");
				fileCSV+=unit+";";
				
			//ritorna i valori: medi, pi� alto e pi� basso
				if(!s.equals("0_10000_faultraw")  ) {
					Float min=stat.min(s, revision);
					Float max=stat.max(s, revision);
					Float media = stat.media(s, revision);
				
				System.out.print(min +"\t" );
				System.out.print(max +"\t");
				System.out.print(media+"\t");
				fileCSV+=min+";"+max+";"+media;
				
				}
				fileCSV+="\n";
				
				
				System.out.println();
				System.out.println("---------------------------------------------------------------------------------------------------------------");
			
		}
		
		
		
		
		//ritorna tutte le diagnostiche che hanno il diagnostic type a null
		
		//lista di diagnostiche che stanno in metrics ma non in diagnostic_list
		
		
		scriviFile(filePath,fileCSV);
	}
	
	
	
public static void avvioDecodificaBinPerform(String path, String incremento) {
		
		String filePath = "";
		String fileDir = path;
		String incrementoFile=incremento;
		String riga = "";
		String fileJson = "";
		String fileTesto="";
		ArrayList<String> files;
		FileWriter w;
		BufferedWriter b;
		
		
		   
		    
		//aprire la directory dei bin
				//prendere la lista dei file nella directory
				files = listFiles(apriFile(fileDir));
				String jsonPath = fileDir.concat("\\Out\\output").concat(incrementoFile).concat(".json");
				String txtPath = fileDir.concat("\\Out\\output".concat(incrementoFile).concat(".txt"));
				File jsonFile = newFile(jsonPath);
				File txtFile = newFile(txtPath);
				
				 try {
						w=new FileWriter(jsonPath);
					
					
				    
				    b=new BufferedWriter (w);

					b.write("");
					
				    b.flush();
				    
				    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
				Integer index=0;
				for (  String fileName  : files){
							
				//leggere il singolo file 
				//codificare il byte in asci
				//costruire la stringa di output
				filePath=fileDir.concat("//").concat(fileName);
				riga=read(filePath);
				
				
//				test conversione
//				int a=101;
//				System.out.print(converti(a));
				
			
				
				//inserire la stringa nel file di output
				fileJson+=riga.concat("\n");
				//INSERIRE INDICE DI AVANZAMENTO <------------------------------------------------------------
				index++;
				System.out.println("index: "+index+" "+riga);
				fileTesto+=formatta(riga).concat("\n");
				//chiususra del for each
				if(index%5000==0) {
					scriviFile(jsonPath, fileJson);
					scriviFile(txtPath,fileTesto);
					fileJson="";
					fileTesto="";
				
				}
				}
				
				
//				System.out.println(fileJson);
				
				//salvare e chiudere il file di output
				
				//per l'ultima scrittura
				scriviFile(jsonPath, fileJson);
				scriviFile(txtPath,fileTesto);
		
	}


public static void testAvro(Schema schema, GenericRecord record) {
	
	
	GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema); 
    ByteArrayOutputStream os = new ByteArrayOutputStream(); 
    try {
        BinaryEncoder e = EncoderFactory.get().binaryEncoder(os, null); 
  		writer.write(record, e);
        e.flush(); 
        byte[] byteData = os.toByteArray(); 
        
    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
    
    finally {
        try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}


public static Integer convertAvroToJson(String filePath, String schemaPath,String jsonPath){
	Integer incremento=0;
	
	try {
		StringBuffer fileJson = new StringBuffer();
		ArrayList<String> fileCostruzione = new ArrayList<String> ();
		Integer index=0;
		
		File jsonFile = newFile(jsonPath);
		ArrayList<String> listfiles = listFiles(apriFile(filePath));
		
		Schema schema = new Schema.Parser().parse(new File(schemaPath));
		DatumReader<GenericRecord> reader = new SpecificDatumReader<GenericRecord>(schema);
		
		
		for(String s:listfiles) {
		
		FileInputStream fis = new FileInputStream(new File(filePath+"\\"+s));
		//legge i byte del file
		byte[] received_message = new byte[fis.available()];
		    fis.read(received_message);

		org.apache.avro.io.Decoder decoder = DecoderFactory.get().binaryDecoder(received_message, null);
        GenericRecord payload2 = null;
        payload2 = reader.read(null, decoder);
        //System.out.println("Message received : " + payload2);


		//inserire la stringa nel file di output
		//fileJson+=payload2.toString().concat("\n");
		fileCostruzione.add(payload2.toString().concat("\n"));
		index++;
		System.out.println("index: "+index+" "+payload2.toString());
//		if(index%2000==0) {
//			System.out.println("Aggiungo all'array e ripulisco");
//			fileCostruzione.add(fileJson);
//			fileJson= new String("");
//		}

		
		
		//fileCostruzione.add(fileJson);

		if (index%100000==0) {
			
			StringBuffer testoJSON= new StringBuffer();
			for(String s1 : fileCostruzione) {
				testoJSON.append(s1);
			}
			
			//per l'ultima scrittura
			scriviFile(jsonPath.concat("_").concat(incremento.toString()), testoJSON.toString());
			
			incremento++;
			fileCostruzione = new ArrayList<String> ();
			
		}
		
		}
		
		
		StringBuffer testoJSON= new StringBuffer();
		for(String s : fileCostruzione) {
			testoJSON.append(s);
		}
		
		//per l'ultima scrittura
		scriviFile(jsonPath.concat("_").concat(incremento.toString()), testoJSON.toString());
		//scriviFile(txtPath,fileTesto);
        
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return incremento;
	
}

public static void calcolaStatistiche(Integer revision, String filePath, String conf) {
	
	//lista delle diagnostiche da controllare
	//creare una lista di diagnosiche
//	ArrayList<String> list = leggiRigheFile(confPath);
	//per ogni diagnostica richiedo i valori statistici calcolati
	DiagnosticListDAO diagnostiche = new DiagnosticListDAO();
	ArrayList<Diagnostica> list = diagnostiche.getConfCompleta();
	
	String fileCSV="Calcololo delle Occorrenze \n \n Diagnostica; Numero di Occorrenze;Policy;DiagnosticType;Unit;Valore Minimo;Valore Massimo; Range MIN ; range MAX; misura;  verificaMin ; verificaMax\n";
	ArrayList<String>  CSV = new 	ArrayList<String>();
	CSV.add(fileCSV);
	System.out.println(conf);
	System.out.println("Calcolo delle occorrenze");
	System.out.println("----------------------------");
	System.out.println("Diagnostica \tNum Occorrenze \tPolicy \t \t \tDiagnosticType \t Unit \tMin \tMax \tRange MIN \trange MAX \tmisura  \tverificaMin  \tverificaMax");
	for (Diagnostica s : list) {
		//vede in base a cosa ricercare
		//per descrizione
		if(conf.compareTo("EVM") == 0) {
			s.setDiagnosticKey(s.getEvmDescription());
		}
		///
		System.out.print(s.getDiagnosticKey() + "\t");
		fileCSV=s.getDiagnosticKey()+";";
//		System.out.print(s.getEvmDescription() + "\t");
		StatMetricaDAO stat = new StatMetricaDAO();
		//ritorna il numero di occorrenze
			Integer occ = stat.contaOccorrenze(s.getDiagnosticKey(), revision);
			System.out.print(occ+"\t \t");
			fileCSV+=occ+";";
		//ritorna le policy
			String policy= stat.policy(s.getDiagnosticKey(), revision);
			System.out.print(policy+"\t");
			fileCSV+=policy+";";
		//ritorna i diagnostictype
			String diaType=stat.diagnosticType(s.getDiagnosticKey(), revision); 
			System.out.print(diaType+"\t");
			fileCSV+=diaType+";";
		
//			ritorna l'unit� di misura
			String unit=stat.unit(s.getDiagnosticKey(), revision);
			System.out.print(unit+"\t");
			fileCSV+=unit+";";
			
		//ritorna i valori: medi, pi� alto e pi� basso
//			if(!s.getInfo().equals("ex") ) {
			if(true) {
				Float min=stat.min(s.getDiagnosticKey(), revision);
				Float max=stat.max(s.getDiagnosticKey(), revision);
				//Float media = stat.media(s, revision);
			
			System.out.print(min +"\t" );
			System.out.print(max +"\t");
			//System.out.print(media+"\t");
			fileCSV+=min+";"+max+";"; // +media;
			if(!s.getInfo().equals("ex") ) {
//			Range MIN ; range MAX; misura;  verificaMin ; verificaMax
			System.out.print(s.getMin() +"\t" );
			System.out.print(s.getMax() +"\t" );
			System.out.print(s.getMisura() +"\t" );
			fileCSV+=s.getMin()+";"+s.getMax()+";"+s.getMisura()+";";
				
			
			if(min<s.getMin() ) {System.out.print(" ERROR \t" ); fileCSV+="ERROR;";}
			else {System.out.print(" OK \t" ); fileCSV+="OK;";}
			
			if(max>s.getMax() ) {System.out.print(" ERROR \t" ); fileCSV+="ERROR;";}
			else {System.out.print(" OK \t" ); fileCSV+="OK;";}
			}
			
			}
			fileCSV+="\n";
			CSV.add(fileCSV);
			
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------------------------");
		
	}
	
	
	
	
	//ritorna tutte le diagnostiche che hanno il diagnostic type a null
	
	//lista di diagnostiche che stanno in metrics ma non in diagnostic_list
	
	String fileReport="";
	
	for(String s : CSV) {
		
		fileReport+=s;
	}
	
	scriviFile(filePath,fileReport);
}



public static void calcolaStatisticheNew(Integer revision, String filePath, String conf) {
	
	//lista delle diagnostiche da controllare
	//creare una lista di diagnosiche
//	ArrayList<String> list = leggiRigheFile(confPath);
	//per ogni diagnostica richiedo i valori statistici calcolati
	StatistcMetricaDAO statDiagnostiche = new StatistcMetricaDAO();
	ArrayList<StatisticMetrica> list = statDiagnostiche.contaOccorrenze(revision);
	Integer diagnostica=0;
	Integer temp=-1;
	
	String fileCSV=conf+"\n";
	ArrayList<String>  CSV = new 	ArrayList<String>();
	CSV.add(fileCSV);
	System.out.println(conf);
	System.out.println("Calcolo delle occorrenze");
	System.out.println("----------------------------");
	for (StatisticMetrica s : list) {
		//vede in base a cosa ricercare
		//per descrizione
		if(conf.compareTo("EVM") == 0) {
			//// TO-DO s.setDiagnosticKey(s.getEvmDescription());
			
		}
		
		diagnostica = s.getD().getId();
		if(diagnostica!=temp)
		{
			//� una diagnostica nuova quindi stampo i valori generici
			//impongo per non ristampare dopo
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			
			temp = diagnostica;
			fileCSV= "\n___________________________________________________________\nDiagnostic:;"+s.getM().getDiagnosticKey();
			fileCSV+="\nDiagnostic Type: " + s.getD().getDiagnosticKey()+ " " + s.getD().getEvmDescription()+ " \nCodifica: " +s.getD().getCodifica()+ " \nScala: " +s.getD().getScala()+ " \nUnit� Misura:" +s.getD().getMisura()+ " \nMax: " +s.getD().getMax()+ " \nMin: " +s.getD().getMin()+ " \nInfo: " +s.getD().getInfo();
			fileCSV+="\nOccorrenze:";
			fileCSV+="\n;Policy;NumOcc ;Max ;Min ;Media ;Unit ;testMax ;testMin ;testUnit ;testQuantizzazione";
			CSV.add(fileCSV);
			
			System.out.println("Diagnostic: "+s.getM().getDiagnosticKey());
			System.out.println("Diagnostic Type: " + s.getD().getDiagnosticKey()+ " " + s.getD().getEvmDescription()+ " \nCodifica: " +s.getD().getCodifica()+ " \nScala: " +s.getD().getScala()+ " \nUnit� Misura:" +s.getD().getMisura()+ " \nMax: " +s.getD().getMax()+ " \nMin: " +s.getD().getMin()+ " \nInfo: " +s.getD().getInfo());
			System.out.println("Occorrenze:");
			System.out.println("\t Policy \t\t\tNumOcc \tMax \tMin \tMedia \tUnit \ttestMax \ttestMin \ttestUnit \ttestQuantizzazione");
			
		}else {
			//� una diagnostica gi� in essere quindi non stampo i valori generici
		}
		
		//stampo il restante
		fileCSV="\n;"+s.getM().getPolicy()+";"+s.getOccorrenze()+";"+s.getMax()+";"+s.getMin()+";"+s.getMedia()+";"+s.getM().getUnit()+";"+ (s.checkMax()?"OK":"ERROR") +";"+(s.checkMin()?"OK":"ERROR")+";"+(s.checkUnit()?"OK":"ERROR")+";"+(s.checkScale()?"OK":"ERROR");
		System.out.println("\t "+s.getM().getPolicy()+"\t\t\t "+s.getOccorrenze()+"\t "+s.getMax()+"\t "+s.getMin()+"\t "+s.getMedia()+"\t "+s.getM().getUnit()+"\t "+ (s.checkMax()?"OK":"ERROR") +"\t "+(s.checkMin()?"OK":"ERROR")+"\t "+(s.checkUnit()?"OK":"ERROR")+"\t "+(s.checkScale()?"OK":"ERROR"));
		
		CSV.add(fileCSV);
			
	}
	
	
	
	
	//ritorna tutte le diagnostiche che hanno il diagnostic type a null
	
	//lista di diagnostiche che stanno in metrics ma non in diagnostic_list
	
	String fileReport="";
	
	for(String s : CSV) {
		
		fileReport+=s;
	}
	
	scriviFile(filePath,fileReport);
}
}
