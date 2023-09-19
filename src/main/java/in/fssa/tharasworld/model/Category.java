package in.fssa.tharasworld.model;

public abstract class Category implements Comparable<Category> {

	private int cateId;
	private String cateName;
	private String img;
	private boolean isActive;

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int compareTo(Category c) {

		if (this.getCateId() == c.getCateId()) {
			return 0;
		} else {
			if (this.cateId > getCateId()) {
				return 1;
			} else {
				return -1;
			}

		}

	}

	@Override
	public String toString() {
		return "Category [cateId=" + cateId + ", cateName=" + cateName + ", imgUrl=" + img + ", isActive=" + isActive
				+ "]";
	}

}
