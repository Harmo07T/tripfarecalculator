package littlepay.tripfarecalculator.model.dto;

/**
 * Determine the type of the tap -  ON or OFF
 * @author Harmonie
 *
 */
public enum TapType {

    ON("ON"), OFF("OFF");

    private final String type;

    TapType(String tapeType) {
        type = tapeType;
    }

	public String getType() {
		return type;
	}
}