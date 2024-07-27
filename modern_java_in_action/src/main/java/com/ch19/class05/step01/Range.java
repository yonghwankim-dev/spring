package com.ch19.class05.step01;

import java.util.Objects;

public class Range{
	private final int start;
	private final int end;

	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	// Tree의 value가 start와 end 사이에 있는지 여부
	public boolean isInRange(Tree tree) {
		return tree.isInRange(start, end);
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		Range range = (Range)object;
		return start == range.start && end == range.end;
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, end);
	}

	@Override
	public String toString() {
		return "Range{" +
			"start=" + start +
			", end=" + end +
			'}';
	}
}
