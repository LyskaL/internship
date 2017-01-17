package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import llyska.util.Constants;

public class TextWithBorder extends Composite {
	private Canvas _canvas;
	private Text _text;
	private Color _color;

	public TextWithBorder(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, true));
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		_color = Constants.GRAY;
		
		_canvas = new Canvas(this, SWT.NONE);
		_canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		_text = new Text(_canvas, SWT.NONE);
		setPaintListener();
	}
	
	public void setEditable(boolean editable) {
		_text.setEnabled(false);
	}
	
	public void changeStyle(int style) {
		_text = new Text(_canvas, style);
	}

	public void setText(String text) {
		if(text != null) {
			_text.setText(text);
		}
	}

	public String getText() {
		return _text.getText();
	}
	
	public void redrawBorder(Color color) {
		_color = color;
		redraw();
	}
	
	private void setPaintListener() {
		_canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(_color);
				int width = 100;
				int height = 20;
				int x = _canvas.getBounds().width-(width+5);
				int y = _canvas.getBounds().height-(height+5);
				e.gc.drawRectangle(x, y, width, height);
				_text.setBounds(x+1, y+1, width-1, height-1);
			}
		});
	}
}
