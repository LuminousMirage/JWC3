package com.hiveworkshop.wc3.units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hiveworkshop.wc3.resources.WEString;

public abstract class HashedGameObject implements GameObject {
	HashMap<StringKey, List<String>> fields = new HashMap<>();
	String id;
	ObjectData parentTable;

	transient HashMap<String, List<GameObject>> hashedLists = new HashMap<>();

	public HashedGameObject(final String id, final ObjectData table) {
		this.id = id;
		parentTable = table;
	}

	@Override
	public void setField(final String field, final String value) {
		final StringKey key = new StringKey(field);
		List<String> list = fields.get(key);
		if (list == null) {
			list = new ArrayList<>();
			fields.put(key, list);
			list.add(value);
		} else {
			list.set(0, value);
		}
	}

	@Override
	public String getField(final String field) {
		final String value = "";
		if (fields.get(new StringKey(field)) != null) {
			final List<String> list = fields.get(new StringKey(field));
			final StringBuilder sb = new StringBuilder();
			if (list != null) {
				for (final String str : list) {
					if (sb.length() != 0) {
						sb.append(',');
					}
					sb.append(str);
				}
				return sb.toString();
//				value = list.get(0);
			}
		}
		return value;
	}

	public boolean hasField(final String field) {
		return fields.containsKey(new StringKey(field));
	}

	@Override
	public int getFieldValue(final String field) {
		int i = 0;
		try {
			i = Integer.parseInt(getField(field));
		} catch (final NumberFormatException e) {

		}
		return i;
	}

	@Override
	public void setField(final String field, final String value, final int index) {
		final StringKey key = new StringKey(field);
		List<String> list = fields.get(key);
		if (list == null) {
			if (index == 0) {
				list = new ArrayList<>();
				fields.put(key, list);
				list.add(value);
			} else {
				throw new IndexOutOfBoundsException();
			}
		} else {
			if (list.size() == index) {
				list.add(value);
			} else {
				list.set(index, value);
			}
		}
	}

	@Override
	public String getField(final String field, final int index) {
		String value = "";
		if (fields.get(new StringKey(field)) != null) {
			final List<String> list = fields.get(new StringKey(field));
			if (list != null) {
				value = list.get(index);
			}
		}
		return value;
	}

	@Override
	public int getFieldValue(final String field, final int index) {
		int i = 0;
		try {
			i = Integer.parseInt(getField(field, index));
		} catch (final NumberFormatException e) {

		}
		return i;
	}

	@Override
	public List<GameObject> getFieldAsList(final String field, final ObjectData parentTable) {
		List<GameObject> fieldAsList;// = hashedLists.get(field);
		// if( fieldAsList == null ) {
		fieldAsList = new ArrayList<>();
		final String stringList = getField(field);
		final String[] listAsArray = stringList.split(",");
		if (listAsArray != null && listAsArray.length > 0) {
			for (final String buildingId : listAsArray) {
				final GameObject referencedUnit = parentTable.get(buildingId);
				if (referencedUnit != null) {
					fieldAsList.add(referencedUnit);
				}
			}
		}
		// hashedLists.put(field, fieldAsList);
		// }
		return fieldAsList;
	}

	@Override
	public String toString() {
		return getField("Name");
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		String name = getField("Name");
		boolean nameKnown = name.length() >= 1;
		if (!nameKnown && !getField("code").equals(id) && getField("code").length() >= 4) {
			final GameObject other = parentTable.get(getField("code").substring(0, 4));
			if (other != null) {
				name = other.getName();
				nameKnown = true;
			}
		}
		if (!nameKnown && getField("EditorName").length() > 1) {
			name = getField("EditorName");
			nameKnown = true;
		}
		if (!nameKnown && getField("Editorname").length() > 1) {
			name = getField("Editorname");
			nameKnown = true;
		}
		if (!nameKnown && getField("BuffTip").length() > 1) {
			name = getField("BuffTip");
			nameKnown = true;
		}
		if (!nameKnown && getField("Bufftip").length() > 1) {
			name = getField("Bufftip");
			nameKnown = true;
		}
		if (nameKnown && name.startsWith("WESTRING")) {
			if (!name.contains(" ")) {
				name = WEString.getString(name);
			} else {
				final String[] names = name.split(" ");
				name = "";
				for (final String subName : names) {
					if (name.length() > 0) {
						name += " ";
					}
					if (subName.startsWith("WESTRING")) {
						name += WEString.getString(subName);
					} else {
						name += subName;
					}
				}
			}
			if (name.startsWith("\"") && name.endsWith("\"")) {
				name = name.substring(1, name.length() - 1);
			}
			setField("Name", name);
		}
		if (!nameKnown) {
			name = WEString.getString("WESTRING_UNKNOWN") + " '" + getId() + "'";
		}
		if (getField("campaign").startsWith("1") && Character.isUpperCase(getId().charAt(0))) {
			name = getField("Propernames");
			if (name.contains(",")) {
				name = name.split(",")[0];
			}
		}
		String suf = getField("EditorSuffix");
		if (suf.length() > 0 && !suf.equals("_")) {
			if (suf.startsWith("WESTRING")) {
				suf = WEString.getString(suf);
			}
			if (!suf.startsWith(" ")) {
				name += " ";
			}
			name += suf;
		}
		return name;
	}

	public void addToList(final String parentId, final String list) {
		String parentField = getField(list);
		if (!parentField.contains(parentId)) {
			parentField = parentField + "," + parentId;
			setField(list, parentField);
		}
	}

	@Override
	public ObjectData getTable() {
		return parentTable;
	}

	@Override
	public Set<String> keySet() {
		final Set<String> keySet = new HashSet<>();
		for (final StringKey key : fields.keySet()) {
			keySet.add(key.getString());
		}
		return keySet;
	}
}
