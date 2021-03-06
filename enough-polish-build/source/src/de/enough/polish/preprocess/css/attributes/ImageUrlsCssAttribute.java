/*
 * Created on Apr 15, 2007 at 10:12:36 PM.
 * 
 * Copyright (c) 2007 Robert Virkus / Enough Software
 *
 * This file is part of J2ME Polish.
 *
 * J2ME Polish is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * J2ME Polish is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with J2ME Polish; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 * Commercial licenses are also available, please
 * refer to the accompanying LICENSE.txt or visit
 * http://www.j2mepolish.org for details.
 */
package de.enough.polish.preprocess.css.attributes;

import de.enough.polish.Environment;
import de.enough.polish.preprocess.css.CssAttribute;
import de.enough.polish.util.StringUtil;

/**
 * <p>An attribute pointing to an image.</p>
 *
 * <p>Copyright Enough Software 2007</p>
 * <pre>
 * history
 *        Apr 15, 2007 - rob creation
 * </pre>
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class ImageUrlsCssAttribute extends CssAttribute {
	
	/**
	 * Creates a new instance.
	 */
	public ImageUrlsCssAttribute() {
		super();
	}

	/* (non-Javadoc)
	 * @see de.enough.polish.preprocess.css.CssAttribute#getValue(java.lang.String, de.enough.polish.Environment)
	 */
	public String getValue(String value, Environment environment ) {
		if ("none".equals(value)) {
			return "null";
		}
		value = super.getValue(value, environment);
		if ( value.startsWith("url") ) {
			int startPos = value.indexOf('(');
			int endPos = value.lastIndexOf(')');
			if (startPos != -1 && endPos != -1) {
				value = value.substring( startPos + 1, endPos ).trim();
			}
		}
		String[] values = StringUtil.splitAndTrim(value, ',');
		StringBuffer buffer = new StringBuffer();
		buffer.append('"');
		for (int i = 0; i < values.length; i++)
		{
			value = values[i];
			if ( value.charAt(0) != '/' ) {
				value = "/" + value;
			}
			buffer.append(value);
			if (i < values.length - 1) {
				buffer.append(',');
			}
		}
		buffer.append('"');		
		return buffer.toString();
	}
}
