package com.hiveworkshop.wc3.gui.modeledit.manipulator.activity;

import com.hiveworkshop.wc3.gui.modeledit.manipulator.ModelEditor;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionView;
import com.hiveworkshop.wc3.mdl.Vertex;

public final class ScaleManipulator extends AbstractScaleManipulator {

	public ScaleManipulator(final ModelEditor modelEditor, final SelectionView selectionView) {
		super(modelEditor, selectionView);
	}

	@Override
	protected final void scaleWithFactor(final ModelEditor modelEditor, final Vertex center, final double scaleFactor,
			final byte dim1, final byte dim2) {
		modelEditor.scale(center.x, center.y, center.z, scaleFactor, scaleFactor, scaleFactor);
	}

}