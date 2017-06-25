package com.bigkoo.pickerview.adapter;

public interface WheelAdapter<T> {
	/**
	 * Gets items count
	 * @return the count of wheel items
	 */
	int getItemsCount();
	
	/**
	 * Gets a wheel item_multichoice by index.
	 * @param index the item_multichoice index
	 * @return the wheel item_multichoice text or null
	 */
	T getItem(int index);
	
	/**
	 * Gets maximum item_multichoice length. It is used to determine the wheel width.
	 * If -1 is returned there will be used the default wheel width.
	 * @param o
	 * @return the maximum item_multichoice length or -1
     */
	int indexOf(T o);
}
