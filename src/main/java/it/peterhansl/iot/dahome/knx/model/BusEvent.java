package it.peterhansl.iot.dahome.knx.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BusEvent {

	private final String id;
	private final long timestamp;
	private final String name;
	private final int address;
	private final String dpt;
	private final byte[] value;
	
	public BusEvent(String name, int address, String dpt, byte[] value) {
		this(UUID.randomUUID().toString(), Instant.now().toEpochMilli(), name, address, dpt, value);
	}
	
}
