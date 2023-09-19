package in.fssa.tharasworld.model;

public abstract class Type implements Comparable<Type> {

	private int typeId;
	private String typeName;
	private String img;
	private int cateId;
	private boolean isActive;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int compareTo(Type t) {

		if (this.getTypeId() == t.getTypeId()) {
			return 0;
		} else {
			if (this.typeId > getTypeId()) {
				return 1;
			} else {
				return -1;
			}

		}

	}

	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", typeName=" + typeName + ", img_url=" + img + ", cateId=" + cateId
				+ ", isActive=" + isActive + "]";
	}

}
