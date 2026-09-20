package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified period from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractPeriod implements Filter {
	
	private int min;
	private int max;

	/**
	 * Builds the Filter from the [min, max] range defining the period that needs to
	 * be extracted. min and max are the indexes of the first and last measurements
	 * of the array obtained by calling the getMeasurements() method of EEGModel,
	 * and represent the starting and ending point of the period to be extracted.
	 * Both indexes are included and max-min must be less than the length of the
	 * Measurements array of the EGG Model.
	 * 
	 * @param min Start of the period to be extracted.
	 * @param max End of the period to be extracted.
	 */
	public FilterExtractPeriod(int min, int max) {
		// TODO
		this.min = min;
		this.max = max;
	} 

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// TODO
		Measurement [] measurement = eeg.getMeasurements();
		int rango = this.max - this.min + 1;
		Measurement [] measurementFiltradas = new Measurement [rango];
		int k=0;
		
		if (this.min >=0 && this.max < measurement.length && this.min<=this.max) {
			
			for(int i=this.min; i<max+1; i++) {
				
				measurementFiltradas[k]=measurement[i];   
				  
				 k++;
				 
			}
		}else { 
			
			System.out.println("max-min must be less than the length of the Measurements array of the EGG Model");
			return null;
		}
		
		EEGModel eegFiltrado = new EEGModel(measurementFiltradas);
		
		return eegFiltrado;
	}
}
