package it.peterhansl.iot.dahome.knx.model;

import java.time.Instant;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tuwien.auto.calimero.dptxlator.DPTXlator;
import tuwien.auto.calimero.dptxlator.TranslatorTypes;
import tuwien.auto.calimero.exception.KNXException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusEvent {

	private  String id;
	private  long timestamp;
	private  String name;
	private  int address;
	private  int mainNumber;
	private  String dpt;
	private  byte[] rawValue;
	
	//@Setter(AccessLevel.PRIVATE)
	private  double numericValue;
	
	//@Setter(AccessLevel.PRIVATE)
	private  String value;
	
	public BusEvent(String name, int address, int mainNumber, String dpt, byte[] rawValue) throws IllegalArgumentException {
		this(UUID.randomUUID().toString(), Instant.now().toEpochMilli(), name, address, mainNumber, dpt, rawValue, 0.0d, "");
		try {
			calcAndSetValue();
		} catch (KNXException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	private void calcAndSetValue() throws KNXException {
		DPTXlator xlator = TranslatorTypes.createTranslator(getMainNumber(), getDpt());
		xlator.setData(getRawValue());
		this.setNumericValue(xlator.getNumericValue());
		this.setValue(xlator.getValue());
	}
	
}
