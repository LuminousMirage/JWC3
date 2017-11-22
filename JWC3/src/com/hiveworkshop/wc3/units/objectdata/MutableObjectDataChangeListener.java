package com.hiveworkshop.wc3.units.objectdata;

public interface MutableObjectDataChangeListener {
	void textChanged(War3ID changedObject);

	void iconsChanged(War3ID changedObject);

	void categoriesChanged(War3ID changedObject);

	void fieldsChanged(War3ID changedObject);

	void modelChanged(War3ID changedObject);

	void objectCreated(War3ID newObject);

	void objectsCreated(War3ID[] newObject);
}
