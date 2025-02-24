package customComponent;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.BigDecimal;

public class scrollableNumericField extends JTextField {
    public scrollableNumericField(int columns) {
        super(columns);
        setHorizontalAlignment(JTextField.LEFT);
        setPreferredSize(new Dimension(100, 30));
        setText("");

        ((AbstractDocument) getDocument()).setDocumentFilter(new NumericDocumentFilter());

        addMouseWheelListener(e -> {
            try {
                if(getText().isEmpty()) {
                    setText("0");
                }

                // Using BigDecimal due to floating point error in double operations
                // Giving up some performance, bummer...
                BigDecimal value = new BigDecimal(getText());

                if(e.getWheelRotation() < 0) {
                    value = value.add(BigDecimal.valueOf(1.0));
                }
                else {
                    value = value.subtract(BigDecimal.valueOf(1.0));
                }

                setText(value.stripTrailingZeros().toPlainString());
            }
            catch (NumberFormatException ex) {
                if(!getText().isEmpty()) {
                    setText("0");
                }
            }
        });
    }

    private static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = oldText.substring(0, offset) + text + oldText.substring(offset + length);

            if(isValidNumber(newText)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
            String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = oldText.substring(0, offset) + text + oldText.substring(offset);

            if(isValidNumber(newText)) {
                super.insertString(fb, offset, text, attrs);
            }
        }

        private boolean isValidNumber(String text) {
            return text.matches("\\d*\\.?\\d*");
        }
    }
}
