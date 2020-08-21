/**
 * @author craig
 *
 */
module ca.footeware.converter.length {
	requires ca.footeware.converter;
	requires swt;
	exports ca.footeware.converter.length;
	provides ca.footeware.converter.spi.ConverterPanel with ca.footeware.converter.length.LengthPanel;
}