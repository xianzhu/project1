package com.kd.model.general;

import com.kd.model.general.base.BaseEntAbnormalItem;

/**
 *
 */
@SuppressWarnings("serial")
public class EntAbnormalItem extends BaseEntAbnormalItem<EntAbnormalItem> implements Comparable<EntAbnormalItem> {
	public static final EntAbnormalItem dao = new EntAbnormalItem();
	@Override
	public int compareTo(EntAbnormalItem ent) {
		int i = ent.getInDate().compareTo(this.getInDate());
		return i;
	}
}
