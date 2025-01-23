/**
 *
 */
package ca.footeware.converter.temperature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
	private ModifyListener cListener;

	/**
	 * Constructor
	 *
	 * @param parent {@link Composite}
	 */
	@Override
	public Composite getPanel(Composite parent) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new FillLayout());

		Composite panel = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(panel);
		panel.addDisposeListener(_ -> dispose());

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 20;
		gridLayout.verticalSpacing = 20;
		panel.setLayout(gridLayout);
		
		Text fText = new Text(panel, SWT.BORDER);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		fText.setLayoutData(gd);

		ModifyListener fListener = _ -> {
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
			cText.removeModifyListener(cListener);
			cText.setText(String.valueOf(c));
			cText.addModifyListener(cListener);
		};

		fText.addModifyListener(fListener);

		Label fLabel = new Label(panel, SWT.NONE);
		fLabel.setText("\u00B0" + "F"); // degrees code
		gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		fLabel.setLayoutData(gd);

		cText = new Text(panel, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		cText.setLayoutData(gd);
		cListener = _ -> {
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
			fText.removeModifyListener(fListener);
			fText.setText(String.valueOf(f));
			fText.addModifyListener(fListener);
		};

		cText.addModifyListener(cListener);

		Label cLabel = new Label(panel, SWT.NONE);
		cLabel.setText("\u00B0" + "C");
		gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		cLabel.setLayoutData(gd);

		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setMinSize(panel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		return scrolledComposite;
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
