package es.upm.aled.lab1.measurements;

import java.util.Iterator;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {
	
	private int [] validChannels; 

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		// TODO
		this.validChannels = validChannels;
		 
	}  
 
	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		
		// TODO 
		
		Measurement [] measurements = eeg.getMeasurements();
		
		Measurement [] measurementsFiltradas = new Measurement [measurements.length];
		
		for(int k=0; k<measurements.length;k++) {
			Measurement m= measurements[k];
			float [] valoresDeseados = new float [validChannels.length]; 
			for(int i=0; i<validChannels.length;i++) {
				for(int j=0; j<m.numChannels();j++) { 
					if(validChannels[i]==j) { 
						valoresDeseados[i] = m.getChannel(j);  
						break; 
					}
					
				}
				
			} 
			
			measurementsFiltradas[k] = new Measurement (valoresDeseados); 
						
		}
		
		EEGModel eegFiltrado = new EEGModel(measurementsFiltradas);
		
		return eegFiltrado;  
	}
	
	
	
 
}
