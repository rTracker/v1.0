package com.rtracker.reservation;

import java.util.Map;

import com.rtracker.entries.ITrackable;

public class SimultaneousReservation extends AReservation {

	public SimultaneousReservation(ITrackable item, int count) {
		super(item);
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	@Override
	public Map<String, String> getAdditionalProperties() {
		Map<String, String> additionalAttributes = super.getAdditionalProperties();
		additionalAttributes.put("count", String.valueOf(count));
		return additionalAttributes;
	}

	@Override
	public void setAdditionalProperties(Map<String, String> additionalProperties) {
		String count = additionalProperties.get("count");
		if (count != null)
			this.count = Integer.valueOf(count);
		else
			this.count = 1;
	}

	private int count;
}
