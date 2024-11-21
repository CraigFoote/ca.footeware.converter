/**
 *
 */
package ca.footeware.converter.length;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * Listens for input in the millimeter field and updates the other fields.
 *
 * @author Footeware.ca
 */
public class MmListener implements ModifyListener {

	private static final String EMPTY = "";
	private LengthPanel lengthPanel;
	private NumberFormat formatter;

	/**
	 * Constructor.
	 *
	 * @param lengthPanel {@link LengthPanel}
	 */
	public MmListener(LengthPanel lengthPanel) {
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

		// update centimeters
		this.lengthPanel.cmText.removeModifyListener(this.lengthPanel.cmListener);
		this.lengthPanel.cmText.setText(isValid ? formatter.format(input / 10) : EMPTY);
		this.lengthPanel.cmText.addModifyListener(this.lengthPanel.cmListener);

		// update meters
		this.lengthPanel.mText.removeModifyListener(this.lengthPanel.mListener);
		this.lengthPanel.mText.setText(isValid ? formatter.format(input / 100) : EMPTY);
		this.lengthPanel.mText.addModifyListener(this.lengthPanel.mListener);

		// update kilometers
		this.lengthPanel.kmText.removeModifyListener(this.lengthPanel.kmListener);
		this.lengthPanel.kmText.setText(isValid ? formatter.format(input / 1000000) : EMPTY);
		this.lengthPanel.kmText.addModifyListener(this.lengthPanel.kmListener);

		// update inches
		this.lengthPanel.inText.removeModifyListener(this.lengthPanel.inListener);
		this.lengthPanel.inText.setText(isValid ? formatter.format(input / 25.4) : EMPTY);
		this.lengthPanel.inText.addModifyListener(this.lengthPanel.inListener);

		// update feet
		this.lengthPanel.ftText.removeModifyListener(this.lengthPanel.ftListener);
		this.lengthPanel.ftText.setText(isValid ? formatter.format(input / 304.8) : EMPTY);
		this.lengthPanel.ftText.addModifyListener(this.lengthPanel.ftListener);

		// update yards
		this.lengthPanel.ydText.removeModifyListener(this.lengthPanel.ydListener);
		this.lengthPanel.ydText.setText(isValid ? formatter.format(input / 914.4) : EMPTY);
		this.lengthPanel.ydText.addModifyListener(this.lengthPanel.ydListener);

		// update miles
		this.lengthPanel.miText.removeModifyListener(this.lengthPanel.miListener);
		this.lengthPanel.miText.setText(isValid ? formatter.format(input / 1.609E+6) : EMPTY);
		this.lengthPanel.miText.addModifyListener(this.lengthPanel.miListener);
	}
}
