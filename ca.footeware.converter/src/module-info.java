/**
 * @author Footeware.ca
 *
 */
module ca.footeware.converter {
	requires transitive org.eclipse.swt.gtk.linux.x86_64;
	exports ca.footeware.converter.spi;
	uses ca.footeware.converter.spi.ConverterPanel;
}