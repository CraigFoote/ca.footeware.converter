/**
 *
 */
package ca.footeware.converter.length;

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
public class LengthPanel extends ConverterPanel {

	Text mmText;
	Text cmText;
	Text mText;
	Text kmText;
	Text inText;
	Text ftText;
	Text ydText;
	Text miText;

	ModifyListener mmListener = new MmListener(this);
	ModifyListener cmListener = new CmListener(this);
	ModifyListener mListener = new MListener(this);
	ModifyListener kmListener = new KmListener(this);
	ModifyListener inListener = new InListener(this);
	ModifyListener ftListener = new FtListener(this);
	ModifyListener ydListener = new YdListener(this);
	ModifyListener miListener = new MiListener(this);

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
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new FillLayout());

		Composite panel = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(panel);
		panel.addDisposeListener(arg0 -> dispose());

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 20;
		gridLayout.verticalSpacing = 20;
		panel.setLayout(gridLayout);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		data.grabExcessHorizontalSpace = true;

		// mm
		mmText = new Text(panel, SWT.BORDER);
		mmText.setLayoutData(data);
		mmText.addModifyListener(mmListener);
		Label mmLabel = new Label(panel, SWT.NONE);
		mmLabel.setText("mm");

		// cm
		cmText = new Text(panel, SWT.BORDER);
		cmText.setLayoutData(data);
		cmText.addModifyListener(cmListener);
		Label cmLabel = new Label(panel, SWT.NONE);
		cmLabel.setText("cm");

		// m
		mText = new Text(panel, SWT.BORDER);
		mText.setLayoutData(data);
		mText.addModifyListener(mListener);
		Label mLabel = new Label(panel, SWT.NONE);
		mLabel.setText("m");

		// km
		kmText = new Text(panel, SWT.BORDER);
		kmText.setLayoutData(data);
		kmText.addModifyListener(kmListener);
		Label kmLabel = new Label(panel, SWT.NONE);
		kmLabel.setText("km");

		// in
		inText = new Text(panel, SWT.BORDER);
		inText.setLayoutData(data);
		inText.addModifyListener(inListener);
		Label inLabel = new Label(panel, SWT.NONE);
		inLabel.setText("in");

		// ft
		ftText = new Text(panel, SWT.BORDER);
		ftText.setLayoutData(data);
		ftText.addModifyListener(ftListener);
		Label ftLabel = new Label(panel, SWT.NONE);
		ftLabel.setText("ft");

		// yd
		ydText = new Text(panel, SWT.BORDER);
		ydText.setLayoutData(data);
		ydText.addModifyListener(ydListener);
		Label ydLabel = new Label(panel, SWT.NONE);
		ydLabel.setText("yd");

		// mi
		miText = new Text(panel, SWT.BORDER);
		miText.setLayoutData(data);
		miText.addModifyListener(miListener);
		Label miLabel = new Label(panel, SWT.NONE);
		miLabel.setText("mi");

		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setMinSize(panel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		return scrolledComposite;
	}
}
