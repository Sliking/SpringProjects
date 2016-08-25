package pt.link.tutorial.cm.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomDatePropertyEditor implements PropertyEditor{

	private SimpleDateFormat dateFormat;
	private CustomDate date;

	public CustomDatePropertyEditor(String datePattern) {
		dateFormat = new SimpleDateFormat(datePattern);
	}

	@Override
	public void setValue(Object value) {
		date = (CustomDate) value;
	}

	@Override
	public Object getValue() {
		return date;
	}

	@Override
	public boolean isPaintable() {
		return false;
	}

	@Override
	public void paintValue(Graphics gfx, Rectangle box) {
		//throw new UnsupportedOperationException();
	}

	@Override
	public String getJavaInitializationString() {
		return "new CustomDate(date); //Where date is java.util.Date";
	}

	@Override
	public String getAsText() {
		CustomDate value = (CustomDate) getValue();
		return (value != null ? this.dateFormat.format(value) : "");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			setValue(new CustomDate(dateFormat.parse(text)));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}

	}

	@Override
	public String[] getTags() {
		return null;
	}

	@Override
	public Component getCustomEditor() {
		return null;
	}

	@Override
	public boolean supportsCustomEditor() {
		return false;
	}


	// Functions fields below were copied from java.beans.PropertyEditorSupport //
	public synchronized void addPropertyChangeListener(
			PropertyChangeListener listener) {
		if (listeners == null) {
			listeners = new java.util.Vector<PropertyChangeListener>();
		}
		listeners.addElement(listener);
	}

	/**
	 * Removes a listener for the value change.
	 * <p>
	 * If the same listener was added more than once,
	 * it will be notified one less time after being removed.
	 * If {@code listener} is {@code null}, or was never added,
	 * no exception is thrown and no action is taken.
	 *
	 * @param listener  the {@link PropertyChangeListener} to remove
	 */
	public synchronized void removePropertyChangeListener(
			PropertyChangeListener listener) {
		if (listeners == null) {
			return;
		}
		listeners.removeElement(listener);
	}

	private java.util.Vector<PropertyChangeListener> listeners;

}
