package pl.filemover.models;

public class CopyStats {
	private int copied;
	private int skipped;
	
	public CopyStats(int copied, int skipped) {
		this.copied = copied;
		this.skipped = skipped;
	}
	
	public int getSkipped() {
		return skipped;
	}
	
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
	
	public int getCopied() {
		return copied;
	}
	
	public void setCopied(int copied) {
		this.copied = copied;
	}
}
