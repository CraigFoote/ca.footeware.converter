/**
 * 
 */
package ca.footeware.converter.length;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import ca.footeware.converter.spi.ConverterPanel;

/**
 * @author Footeware.ca
 *
 */
public class LengthPanel extends ConverterPanel {

	private Text iText;
	private Text mText;
	private List iList;
	private List mList;

	@Override
	public String getLabel() {
		return "Length";
	}

	@Override
	public Image getImage() {
		return getImage("/icons8-ruler-48.png");
	}

	@Override
	public Composite getPanel(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		panel.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				dispose();
			}
		});

		panel.setLayout(new GridLayout(3, false));

		Group iGroup = new Group(panel, SWT.BORDER);
		iGroup.setText("Imperial");
		iGroup.setLayout(new GridLayout());

		iText = new Text(iGroup, SWT.BORDER);
		GridData gd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd.widthHint = 200;
		iText.setLayoutData(gd);

		iList = new List(iGroup, SWT.BORDER | SWT.V_SCROLL);
		iList.setItems("Inch", "Foot", "Mile");
		iList.setSelection(0);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		iList.setLayoutData(gd);

		Composite btnComp = new Composite(panel, SWT.NONE);
		btnComp.setLayout(new FillLayout(SWT.VERTICAL));
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);

		Button i_to_m_button = new Button(btnComp, SWT.PUSH);
		i_to_m_button.setText("To Metric ->");
		i_to_m_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (iText.getText().isBlank()) {
					return;
				}
				String iUnit = iList.getSelection()[0];
				String mUnit = mList.getSelection()[0];
				String iValue = iText.getText().trim();
				Double i = Double.parseDouble(iValue);
				Double m = calculateMetric(i, iUnit, mUnit);
				mText.setText(String.valueOf(m));
			}
		});

		Button m_to_i_button = new Button(btnComp, SWT.PUSH);
		m_to_i_button.setText("<- To Imperial");
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		m_to_i_button.setLayoutData(gd);
		m_to_i_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mText.getText().isBlank()) {
					return;
				}
				String iUnit = iList.getSelection()[0];
				String mUnit = mList.getSelection()[0];
				String mValue = mText.getText().trim();
				Double m = Double.parseDouble(mValue);
				Double i = calculateImperial(m, iUnit, mUnit);
				iText.setText(String.valueOf(i));
			}
		});

		Group mGroup = new Group(panel, SWT.BORDER);
		mGroup.setText("Metric");
		mGroup.setLayout(new GridLayout());

		mText = new Text(mGroup, SWT.BORDER);
		gd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd.widthHint = 200;
		mText.setLayoutData(gd);

		mList = new List(mGroup, SWT.BORDER | SWT.SCROLL_LINE | SWT.V_SCROLL);
		mList.setItems("Millimeter", "Centimeter", "Meter", "Kilometer");
		mList.setSelection(0);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		mList.setLayoutData(gd);

		return panel;
	}

	/**
	 * Convert the Imperial value in provided units given the Metric value and
	 * units.
	 * 
	 * @param m     {@link Double} metric value
	 * @param iUnit {@link String} Imperial units
	 * @param mUnit {@link String} Metric units
	 * @return {@link Double} the Imperial equivalent
	 */
	protected Double calculateImperial(Double m, String iUnit, String mUnit) {
		Double i = switch (mUnit) {
		case ("Millimeter") -> switch (iUnit) {
			case ("Inch") -> m * 0.0393700787;
			case ("Foot") -> m * 0.0032808399;
			case ("Mile") -> m * 6.213688756E-7;
			default -> throw new IllegalArgumentException("Unexpected value: " + iUnit);
			};
		case ("Centimeter") -> switch (iUnit) {
			case ("Inch") -> m * 0.3937007874;
			case ("Foot") -> m * 0.032808399;
			case ("Mile") -> m * 0.0000062137;
			default -> throw new IllegalArgumentException("Unexpected value: " + iUnit);
			};
		case ("Meter") -> switch (iUnit) {
			case ("Inch") -> m * 39.37007874;
			case ("Foot") -> m * 3.280839895;
			case ("Mile") -> m * 0.0006213689;
			default -> throw new IllegalArgumentException("Unexpected value: " + iUnit);
			};
		case ("Kilometer") -> switch (iUnit) {
			case ("Inch") -> m * 39370.07874;
			case ("Foot") -> m * 3280.839895;
			case ("Mile") -> m * 0.6213688756;
			default -> throw new IllegalArgumentException("Unexpected value: " + iUnit);
			};
		default -> throw new IllegalArgumentException("Unexpected value: " + mUnit);
		};
		return i;
	}

	/**
	 * Convert the Metric value in provided units given the Imperial value and
	 * units.
	 * 
	 * @param i     {@link Double} IMperial value
	 * @param iUnit {@link String} Imperial units
	 * @param mUnit {@link String} Metric units
	 * @return {@link Double} the Metric equivalent
	 */
	protected Double calculateMetric(Double i, String iUnit, String mUnit) {
		Double m = switch (iUnit) {
		case ("Inch") -> switch (mUnit) {
			case ("Millimeter") -> i * 25.4;
			case ("Centimeter") -> i * 2.54;
			case ("Meter") -> i * 0.0254;
			case ("Kilometer") -> i * 0.0000254;
			default -> throw new IllegalArgumentException("Unexpected value: " + mUnit);
			};
		case ("Foot") -> switch (mUnit) {
			case ("Millimeter") -> i * 304.8;
			case ("Centimeter") -> i * 30.48;
			case ("Meter") -> i * 0.3048;
			case ("Kilometer") -> i * 0.0003048;
			default -> throw new IllegalArgumentException("Unexpected value: " + mUnit);
			};
		case ("Mile") -> switch (mUnit) {
			case ("Millimeter") -> i * 1609350;
			case ("Centimeter") -> i * 160935;
			case ("Meter") -> i * 1609.35;
			case ("Kilometer") -> i * 1.60935;
			default -> throw new IllegalArgumentException("Unexpected value: " + mUnit);
			};
		default -> throw new IllegalArgumentException("Unexpected value: " + iUnit);
		};
		return m;
	}

}
