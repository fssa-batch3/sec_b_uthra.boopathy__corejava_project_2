package in.fssa.tharasworld.model;

public abstract class Size implements Comparable<Size> {

	private int sizeId;
	private String sizeName;
	private boolean isActive;

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int compareTo(Size s) {

		if (this.getSizeId() == s.getSizeId()) {
			return 0;
		} else {
			if (this.sizeId > getSizeId()) {
				return 1;
			} else {
				return -1;
			}

		}

	}

	@Override
	public String toString() {
		return "Size [sizeId=" + sizeId + ", sizeName=" + sizeName + ", isActive=" + isActive + "]";
	}

}
