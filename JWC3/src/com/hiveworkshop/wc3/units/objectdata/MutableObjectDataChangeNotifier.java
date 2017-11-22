package com.hiveworkshop.wc3.units.objectdata;

import com.etheller.util.SubscriberSetNotifier;

public final class MutableObjectDataChangeNotifier extends SubscriberSetNotifier<MutableObjectDataChangeListener>
		implements MutableObjectDataChangeListener {

	@Override
	public void textChanged(final War3ID changedObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.textChanged(changedObject);
		}
	}

	@Override
	public void categoriesChanged(final War3ID changedObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.categoriesChanged(changedObject);
		}
	}

	@Override
	public void iconsChanged(final War3ID changedObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.iconsChanged(changedObject);
		}
	}

	@Override
	public void fieldsChanged(final War3ID changedObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.fieldsChanged(changedObject);
		}
	}

	@Override
	public void modelChanged(final War3ID changedObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.modelChanged(changedObject);
		}
	}

	@Override
	public void objectCreated(final War3ID newObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.objectCreated(newObject);
		}
	}

	@Override
	public void objectsCreated(final War3ID[] newObject) {
		for (final MutableObjectDataChangeListener listener : set) {
			listener.objectsCreated(newObject);
		}
	}

}
