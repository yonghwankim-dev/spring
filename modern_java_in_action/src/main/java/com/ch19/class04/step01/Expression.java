package com.ch19.class04.step01;

public class Expression {
	public interface Expr{}

	public static class Number implements Expr{
		private final int value;

		public Number(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}

	public static class BinOp implements Expr{
		private final String opName;
		private final Expr left;
		private final Expr right;

		public BinOp(String opName, Expr left, Expr right) {
			this.opName = opName;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return String.format("(%s%s%s)", left, opName, right);
		}
	}

	public static Expr simplifyExpression(Expr expr){
		if (expr instanceof Number){
			return expr;
		}
		if (expr instanceof BinOp){
			BinOp binOp = (BinOp)expr;
			Expr left = simplifyExpression(binOp.left);
			Expr right = simplifyExpression(binOp.right);

			if (left instanceof Number && right instanceof Number){
				if ("+".equals(binOp.opName)){
					int leftValue = ((Number)left).value;
					int rightValue = ((Number)right).value;
					if (rightValue == 0){
						return new Number(leftValue);
					}
				}
			}
			return new BinOp(binOp.opName, left, right);
		}
		return expr;
	}

	public static void main(String[] args) {
		Expr seven = new BinOp("+", new Number(7), new Number(0));
		Expr five = new BinOp("+", new Number(2), new Number(3));
		// (7 + 0) + (2 + 3)
		Expr twelve = new BinOp("+", seven, five);
		System.out.println("Original Expression: " + twelve);
		Expr simplifiedExpression = simplifyExpression(twelve);
		System.out.println("Simplified Expression: " + simplifiedExpression);
	}
}
