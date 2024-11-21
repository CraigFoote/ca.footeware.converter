/**
 *
 */
package ca.footeware.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import ca.footeware.converter.spi.ConverterPanel;

/**
 * Show tabs with panels that convert something in some way.
 * 
 * @author Footeware.ca
 */
public class Converter {

	private static Image icon;

	/**
	 * Application entry point.
	 * 
	 * @param args {@link String}[]
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Footeware Converter");
		shell.setSize(800, 600);
		shell.addDisposeListener(e -> {
			if (icon != null && !icon.isDisposed()) {
				icon.dispose();
			}
		});
		shell.setLayout(new FillLayout());

		TabFolder tabFolder = new TabFolder(shell, SWT.TOP);

		// find all the ConverterPanel implementations & put them in a list
		List<ConverterPanel> panels = new ArrayList<>();
		ServiceLoader<ConverterPanel> converterPanelLoader = ServiceLoader.load(ConverterPanel.class);
		for (ConverterPanel converterPanel : converterPanelLoader) {
			panels.add(converterPanel);
		}

		// sort by tab names
		panels.sort((o1, o2) -> o1.getLabel().compareTo(o2.getLabel()));

		for (ConverterPanel converterPanel : panels) {
			TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
			tabItem.setText(converterPanel.getLabel());
			tabItem.setImage(converterPanel.getImage());
			tabItem.setControl(converterPanel.getPanel(tabFolder));
		}

		tabFolder.pack();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
