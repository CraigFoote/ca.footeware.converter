/**
 * 
 */
package ca.footeware.converter.temperature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ca.footeware.converter.spi.ConverterPanel;

/**
 * @author Footeware.ca
 *
 */
public class TemperaturePanel extends ConverterPanel {

	private Text cText;
	private Text fText;

	/**
	 * Constructor
	 * 
	 * @param parent {@link Composite}
	 */
	public Composite getPanel(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		panel.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				dispose();
			}
		});

		panel.setLayout(new GridLayout(5, false));
		fText = new Text(panel, SWT.BORDER);
		GridData gd = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 2);
		fText.setLayoutData(gd);
		gd.widthHint = 200;
		Label fLabel = new Label(panel, SWT.NONE);
		fLabel.setText("\u00B0" + "F");
		gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
		fLabel.setLayoutData(gd);

		Button button = new Button(panel, SWT.PUSH);
		button.setText("To Celsius ->");
		gd = new GridData(SWT.FILL, SWT.FILL, true, false);
		button.setLayoutData(gd);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = fText.getText();
				if (text.isBlank()) {
					return;
				}
				double f;
				try {
					f = Double.parseDouble(text);
				} catch (NumberFormatException nfe) {
					return;
				}
				double c = f * 1.8 + 32;
				cText.setText(String.valueOf(c));
			}
		});

		cText = new Text(panel, SWT.BORDER);
		gd = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 2);
		cText.setLayoutData(gd);
		gd.widthHint = 200;
		Label cLabel = new Label(panel, SWT.NONE);
		cLabel.setText("\u00B0" + "C");
		gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
		cLabel.setLayoutData(gd);

		button = new Button(panel, SWT.PUSH);
		button.setText("<- To Fahrenheit");
		gd = new GridData(SWT.FILL, SWT.FILL, true, false);
		button.setLayoutData(gd);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = cText.getText();
				if (text.isBlank()) {
					return;
				}
				double c;
				try {
					c = Double.parseDouble(text);
				} catch (NumberFormatException nfe) {
					return;
				}
				double f = (c - 32) / 1.8;
				fText.setText(String.valueOf(f));
			}
		});
		return panel;
	}

	@Override
	public Image getImage() {
		return getImage("/temp.png");
	}

	@Override
	public String getLabel() {
		return "Temperature";
	}

}
