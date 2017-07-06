package com.kd.model.general;

import com.kd.model.general.base.BaseEntSoftCopyrights;

/**
 *
 */
@SuppressWarnings("serial")
public class EntSoftCopyrights extends BaseEntSoftCopyrights<EntSoftCopyrights> implements Comparable<EntSoftCopyrights>{
	public static final EntSoftCopyrights dao = new EntSoftCopyrights();

	@Override
	public int compareTo(EntSoftCopyrights ent) {
		int i = ent.getAppDate().compareTo(this.getAppDate());
		return i;
	}
}
