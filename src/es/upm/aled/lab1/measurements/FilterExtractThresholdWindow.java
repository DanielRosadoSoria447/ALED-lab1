package es.upm.aled.lab1.measurements;

public class FilterExtractThresholdWindow implements Filter {
	
	private int channel;
	private float umbral;
	
	
	public FilterExtractThresholdWindow(int channel, float umbral) {
		
		this.channel = channel;
		this.umbral = umbral;
	}
	
	
	@Override 
	public EEGModel applyFilter(EEGModel eeg) { 
		
		Measurement[] medidas = eeg.getMeasurements();
		
		
		//El tamaño seria 201 siempre que hubiera muestras suficientes, por eso hay que modificarlo
		//Measurement [] measurement = new Measurement [201]; 
		
		int  k=0; 
		
		int indiceMuestra = -1;// Marcamos -1 para saber si se encontró o no
		
		
		for(int i=0; i<medidas.length; i++) {
			float sample = medidas[i].getChannel(this.channel);
			if(sample > this.umbral) {
				indiceMuestra = i;  
				break; 
				
			}
			
		}
		
		if(indiceMuestra == -1) {
			System.out.println("No se encontró ninguna muestra que supere el umbral");
			return null;
		}
		
		//Control de límites seguros (evita índices negativos como -100)
		
		int inicio = Math.max(0, indiceMuestra-100); 
		int fin = Math.min(medidas.length, indiceMuestra+101);     
		
		Measurement [] measurement = new Measurement [fin-inicio];  
		
		for(int i=inicio; i<fin; i++) {   
			
			measurement[k]=medidas[i]; 
			k++;
			
		}
		
		//Los limites están bien siempre y cuando haya medidas suficientes
		//Por ello, hay que cambiar los limites por si no hay tantas medidas
		
		//for(int i=indiceMuestra-100; i<indiceMuestra+101; i++) {   
					 
			//measurement[k]=medidas[i]; 
			//k++;
			
		//}
		
		EEGModel eegFinal = new EEGModel(measurement); 
				
		return eegFinal; 
	} 

}
