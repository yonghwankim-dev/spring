package com.ch09.class03.step01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {
	@DisplayName("사용자는 물체를 오른쪽으로 이동시킨다")
	@Test
	void testMoveRightBy(){
	    // given
		Point point = new Point(10, 10);
		// when
		Point movedPoint = point.moveRightBy(10);
		// then
		Assertions.assertThat(movedPoint.getX()).isEqualTo(20);
		Assertions.assertThat(movedPoint.getY()).isEqualTo(10);
	}

	@DisplayName("사용자는 두 포인트의 위치를 비교한다")
	@Test
	void testComparingTwoPoints(){
	    // given
		Point point1 = new Point(10, 15);
		Point point2 = new Point(10, 20);
		// when
		int result = Point.compareByXAndThenY.compare(point1, point2);
		// then
		Assertions.assertThat(result < 0).isTrue();
	}

	@DisplayName("사용자는 모든 포인트를 오른쪽으로 이동시킨다")
	@Test
	void testMoveAllPointsRightBy(){
	    // given
	    List<Point> points = List.of(new Point(5, 5), new Point(10, 5));
		List<Point> expectedPoints = List.of(new Point(15, 5), new Point(20, 5));
	    // when
		List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
		// then
		Assertions.assertThat(newPoints).isEqualTo(expectedPoints);
	}
}
