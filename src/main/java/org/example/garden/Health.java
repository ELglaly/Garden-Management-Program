package org.example.garden;

// Enum for Health, can be expanded to have more functionality
public enum Health {
	GOOD("Healthy"),
	BAD("Unhealthy");

	private final String status;

	Health(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
