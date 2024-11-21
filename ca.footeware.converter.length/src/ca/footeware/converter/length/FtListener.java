package ca.footeware.converter.length;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * Listens for input in the foot field and updates the other fields.
 *
 * @author Footeware.ca
 */
public class FtListener implements ModifyListener {

	private static final String EMPTY = "";
	private LengthPanel lengthPanel;
	private NumberFormat formatter;

	/**
	 * Constructor.
	 *
	 * @param lengthPanel {@link LengthPanel}
	 */
	public FtListener(LengthPanel lengthPanel) {
		this.lengthPanel = lengthPanel;
		formatter = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		formatter.setMaximumFractionDigits(340);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		String inputStr = ((Text) e.getSource()).getText().trim();
		Double input = null;
		if (!inputStr.isEmpty()) {
			try {
				input = Double.parseDouble(inputStr);
			} catch (NumberFormatException e1) {
				// do nothing
			}
		}

		boolean isValid = (input != null && !input.isNaN());

		// update millimeters
		this.lengthPanel.mmText.removeModifyListener(this.lengthPanel.mmListener);
		this.lengthPanel.mmText.setText(isValid ? String.valueOf(input * 304.8) : EMPTY);
		this.lengthPanel.mmText.addModifyListener(this.lengthPanel.mmListener);

		// update centimeters
		this.lengthPanel.cmText.removeModifyListener(this.lengthPanel.cmListener);
		this.lengthPanel.cmText.setText(isValid ? String.valueOf(input * 30.48) : EMPTY);
		this.lengthPanel.cmText.addModifyListener(this.lengthPanel.cmListener);

		// update meters
		this.lengthPanel.mText.removeModifyListener(this.lengthPanel.mListener);
		this.lengthPanel.mText.setText(isValid ? String.valueOf(input / 3.281) : EMPTY);
		this.lengthPanel.mText.addModifyListener(this.lengthPanel.mListener);

		// update kilometers
		this.lengthPanel.kmText.removeModifyListener(this.lengthPanel.kmListener);
		this.lengthPanel.kmText.setText(isValid ? String.valueOf(input / 3281) : EMPTY);
		this.lengthPanel.kmText.addModifyListener(this.lengthPanel.kmListener);

		// update inches
		this.lengthPanel.inText.removeModifyListener(this.lengthPanel.inListener);
		this.lengthPanel.inText.setText(isValid ? String.valueOf(input * 12) : EMPTY);
		this.lengthPanel.inText.addModifyListener(this.lengthPanel.inListener);

		// update yards
		this.lengthPanel.ydText.removeModifyListener(this.lengthPanel.ydListener);
		this.lengthPanel.ydText.setText(isValid ? formatter.format(input / 3) : EMPTY);
		this.lengthPanel.ydText.addModifyListener(this.lengthPanel.ydListener);

		// update miles
		this.lengthPanel.miText.removeModifyListener(this.lengthPanel.miListener);
		this.lengthPanel.miText.setText(isValid ? String.valueOf(input / 5280) : EMPTY);
		this.lengthPanel.miText.addModifyListener(this.lengthPanel.miListener);
	}
}
