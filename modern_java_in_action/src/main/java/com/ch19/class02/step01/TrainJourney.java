package com.ch19.class02.step01;

public class TrainJourney {
	public int price;
	public TrainJourney onward;

	public TrainJourney(int price, TrainJourney onward) {
		this.price = price;
		this.onward = onward;
	}

	@Override
	public String toString() {
		return "TrainJourney{" +
			"price=" + price +
			", onward=" + (onward != null ? onward.toString() : "null") +
			'}';
	}

	public static TrainJourney link(TrainJourney a, TrainJourney b){
		if (a == null){
			return b;
		}
		TrainJourney t = a;
		while (t.onward != null){
			t = t.onward;
		}
		t.onward = b;
		return a;
	}

	public static TrainJourney append(TrainJourney a, TrainJourney b){
		return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
	}

	public static void main(String[] args) {
		TrainJourney journeyXY = new TrainJourney(1000, null);
		TrainJourney journeyYZ = new TrainJourney(1500, null);
		TrainJourney journeyXZ = link(journeyXY, journeyYZ);
		System.out.println(journeyXZ); // output : TrainJourney{price=1000, onward=TrainJourney{price=1500, onward=null}}
		System.out.println(journeyXY); // output : TrainJourney{price=1000, onward=TrainJourney{price=1500, onward=null}}

		journeyXY = new TrainJourney(1000, null);
		journeyYZ = new TrainJourney(1500, null);
		journeyXZ = append(journeyXY, journeyYZ);
		System.out.println(journeyXZ); // output : TrainJourney{price=1000, onward=TrainJourney{price=1500, onward=null}}
		System.out.println(journeyXY); // output : TrainJourney{price=1000, onward=null}

		// journeyYZ 객체 price 변경
		journeyXZ.onward.price = 3000;
		System.out.println(journeyYZ); // output : TrainJourney{price=3000, onward=null}
	}
}
