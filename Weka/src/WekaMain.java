

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
public class WekaMain {

	public static void main(String[] args) {
		
		clasificador();

	}
	
	/*
	 * Esta funci�n clasificador, permite a trav�s del dataset de entrenamiento,
	 * enviar un dataset de prueba para descubrir y asignar una clase  
	 */
	public static void clasificador(){
		
		
		BufferedReader reader1;
		BufferedReader reader2;
		try {
			reader1 = new BufferedReader(
					new FileReader("/Users/Docente/Desktop/Weka/Senti/FellingsEntrenamiento.arff"));
				 
			reader2 = new BufferedReader(
					new FileReader("/Users/Docente/Desktop/Weka/Senti/FellingsTest.arff"));
		 Instances train = new Instances(reader1);
		 train.setClassIndex(train.numAttributes() - 1); 
			 
		 
		 Instances test = new Instances(reader2);
		 test.setClassIndex(train.numAttributes() - 1); 
			 
		 
		 NaiveBayes model=new NaiveBayes();
		 model.buildClassifier(train);
		 
		 //classify
		 
		 Instances labeled = new Instances(test);
		 
		 for (int i = 0; i < test.numInstances(); i++){
			 double clsLabel = model.classifyInstance(test.instance(i));
			 labeled.instance(i).setClassValue(clsLabel);
		 }
		 
		
		 
		 Evaluation eval_train = new Evaluation(test);
		 eval_train.evaluateModel(model,test);
		 
		 reader1.close();
		 reader2.close();
	
		 
		 String[] options = new String[4];
		 options[0] = "-t"; //name of training file
		 options[1] = "/Users/Docente/Desktop/Weka/Senti/FellingsEntrenamiento.arff";
		 options[2] = "-T";
		 options[3] = "/Users/Docente/Desktop/Weka/Senti/FellingsTest.arff";		 
		 System.out.println(Evaluation.evaluateModel(model, options));
		 

		 
		 BufferedWriter writer = new BufferedWriter(
				 new FileWriter("/Users/Docente/Desktop/Weka/Senti/FellingsNew.arff"));
		 writer.write(labeled.toString());
		 
		 writer.close();
		 
		}
		 catch (Exception e) {
				e.printStackTrace();
		}
    }

}

/*
 
		// C�digo que permite subir la informaci�n de todos los archivos que tiene en una carpeta, delimit�ndolos con un punto
		public static void clasificador(){ 
		String sDirectorio = "C:\\Users\\Docente\\Desktop\\Weka\\Archivos_carga";
		
		BufferedReader reader1;
		
		try {
			File f = new File (sDirectorio);
			if (f.exists()){
				File [] ficheros = f.listFiles();
				   BufferedWriter writer = new BufferedWriter(
							 new FileWriter("/Users/Docente/Desktop/Weka/Archivos_carga/1"+"resultado.txt"));
					
				for(int x=0; x<ficheros.length;x++){
					
					reader1 = new BufferedReader(
							new FileReader("/Users/Docente/Desktop/Weka/Archivos_carga/"+ficheros[x].getName()));
					
					String [] temp; 
					String delimiter = "[.]"+ " ";
				
				    temp = (String.valueOf(reader1.readLine())).split(delimiter);
				 
					for(int i =0; i < temp.length ; i++)
						{System.out.println(temp[i]);
						writer.write(temp[i] + "\n");
						}
					
				 reader1.close();	
					
				}
				 writer.close();
				
			}
			
		
			}catch (Exception e) {
				e.printStackTrace();
			}
    	}
}*/
