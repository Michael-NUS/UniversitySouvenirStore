/**
 * 
 */
package sg.edu.nus.iss.universitysouvenirstore.util;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * @author linwei
 *
 */
public class DoubleTextField extends JTextField{


    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public DoubleTextField()
	{
        super();
      
    }

    public DoubleTextField( int cols ) {
        super( cols );
    }

    @Override
    protected Document createDefaultModel() {
        return new UpperCaseDocument();
    }

 


	static class UpperCaseDocument extends PlainDocument {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public void insertString( int offs, String str, AttributeSet a )
                throws BadLocationException {

            if ( str == null ) {
                return;
            }

            char[] chars = str.toCharArray();
            boolean ok = true;

            for ( int i = 0; i < chars.length; i++ ) {

                try {
                	String tmp = String.valueOf( chars[i] );
                
					if (!tmp.equals(".")) {
						Integer.parseInt(String.valueOf(chars[i]));
					}
                
                } catch ( NumberFormatException exc ) {
                    ok = false;
                    break;
                }


            }

            if ( ok )
                super.insertString( offs, new String( chars ), a );

        }
    }

}